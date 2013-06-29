using System;
using System.Threading;

namespace CodeBasics
{
    public delegate void TaskDelegate();

    public static class ActiveObject
    {
        static readonly ManualResetEvent taskCompletedEvent = new ManualResetEvent(true);
        static Exception exception = null;

        public static bool PerformTask(TaskDelegate Task)
        {
            if (null != Task && taskCompletedEvent.WaitOne(0))
            {
                taskCompletedEvent.Reset();
                exception = null;
                if (ThreadPool.QueueUserWorkItem(PerformTask, Task))
                    return true;
                else
                    this.taskCompletedEvent.Set();
            }
            return false;
        }

        public static void WaitToComplete()
        {
            this.taskCompletedEvent.WaitOne();
        }

        public static Boolean TaskCompleted
        {
            get { return taskCompletedEvent.WaitOne(0); }
        }

        public static Boolean CompletedWithError
        {
            get { return (TaskCompleted && (null != exception)); }
        }

        public static Exception Error
        {
            get { return (CompletedWithError ? exception : null); }
        }

        static void PerformTask(object state)
        {
            TaskDelegate Task = (TaskDelegate)state;
            try
            {
                Task.Invoke();
            }
            catch (Exception exc)
            {
                exception = exc;
            }
            finally
            {
                taskCompletedEvent.Set();
            }
        }
    }
}