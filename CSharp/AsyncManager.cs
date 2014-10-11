using System;
using System.Threading;

namespace CodeBasics
{
    public delegate void TaskDelegate();

    public class AsyncManager
    {
        readonly ManualResetEvent taskCompletedEvent;
        Exception exception;

        public AsyncManager()
        {
            this.taskCompletedEvent = new ManualResetEvent(true);
            this.exception = null;
        }

        public bool StartAsyncTask(TaskDelegate Task)
        {
            if (null != Task && this.taskCompletedEvent.WaitOne(0, false))
            {
                this.taskCompletedEvent.Reset();
                this.exception = null;
                if (ThreadPool.QueueUserWorkItem(this.PerformTask, Task))
                    return true;
                else
                    this.taskCompletedEvent.Set();
            }
            return false;
        }

        public void WaitToComplete()
        {
            this.taskCompletedEvent.WaitOne();
        }

        public bool TaskCompleted
        {
            get { return this.taskCompletedEvent.WaitOne(0, false); }
        }

        public bool CompletedWithError
        {
            get { return (this.TaskCompleted && (null != this.exception)); }
        }

        public Exception Error
        {
            get { return (this.CompletedWithError ? this.exception : null); }
        }

        void PerformTask(object state)
        {
            TaskDelegate Task = (TaskDelegate)state;
            try
            {
                Task.Invoke();
            }
            catch (Exception exc)
            {
                this.exception = exc;
            }
            finally
            {
                this.taskCompletedEvent.Set();
            }
        }
    }
}