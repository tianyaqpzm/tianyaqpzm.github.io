class Solution:
    def gcd(self, a: int, b: int) -> int:
        smaller = min(a, b)
        while smaller:
            if a % smaller == 0 and b % smaller == 0:
                return smaller
            smaller -= 1


# 计算 a除以b 余c， 求出b和c的公约数

    def gcd2(self, a: int, b: int) -> int:
        return a if b == 0 else self.gcd2(b, a % b)

test = Solution()
a = test.gcd2(2, 8)
print(a)