class Heap:
      def __init__(self):
            self.tree = []
      
      def IsEmpty(self):
            return len(self.tree) == 0
      
      def Insert(self, x):
            self.tree.append(x)
            j = len(self.tree) - 1
            i = (j - 1) / 2
            while j > 0 and self.tree[i] > self.tree[j]:
                  temp = self.tree[i]
                  self.tree[i] = self.tree[j]
                  self.tree[j] = temp
                  j = i
                  i = (j - 1) / 2
      
      def ExtractMin(self):
            if len(self.tree) == 0:
                  raise Exception('Heap is empty!')
            x = self.tree[0]
            if len(self.tree) == 1:
                  self.tree.pop()
            else:
                  self.tree[0] = self.tree.pop()
                  i = 0
                  j = 2 * i + 1
                  k = j + 1
                  while j < len(self.tree) and self.tree[i] > self.tree[j] or k < len(self.tree) and self.tree[i] > self.tree[k]:
                        s = j if self.tree[j] < (self.tree[k] if k < len(self.tree) else self.tree[j] + 1) else k
                        temp = self.tree[i]
                        self.tree[i] = self.tree[s]
                        self.tree[s] = temp
                        i = s
                        j = 2 * i + 1
                        k = j + 1
            return x
