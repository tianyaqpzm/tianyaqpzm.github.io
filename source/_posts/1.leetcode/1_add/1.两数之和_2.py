# @lc app=leetcode.cn id=1 lang=python


# @lc code=start
class Solution(object):
    def twoSum(self, num, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        # 保存下标
        index = []
        # 深拷贝并排序
        numtosort = num[:]
        numtosort.sort()
        i = 0
        j = len(numtosort) - 1
        #         print numtosort
        while i < j:
            if numtosort[i] + numtosort[j] == target:
                #                 分别从首尾进行查找下标
                for k in range(0, len(num)):
                    if num[k] == numtosort[i]:
                        index.append(k)
                        break
                for k in range(len(num) - 1, -1, -1):
                    if num[k] == numtosort[j]:
                        index.append(k)
                        break
                index.sort()
                break
            elif numtosort[i] + numtosort[j] < target:
                i = i + 1
            elif numtosort[i] + numtosort[j] > target:
                j = j - 1
        return (index[0], index[1])


# @lc code=end
