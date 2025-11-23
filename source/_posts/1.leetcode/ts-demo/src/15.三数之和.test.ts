
import { threeSum } from './15.三数之和';

describe('threeSum', () => {
    test('三数之和，不重复 [-1,1,1,2,0]', () => {
        expect(threeSum([-1, 1, 1, 2, 0])).toEqual([[-1, 0, 1]]);
    });

    test('三数之和，不重复 [-1,0,1,2,-1,-4]', () => {
        const result = threeSum([-1, 0, 1, 2, -1, -4]);
        const expected = [[-1, -1, 2], [-1, 0, 1]];

        // 方法2：使用 toContainEqual 检查每个期望的结果是否存在
        expect(result).toHaveLength(expected.length);
        expected.forEach(expectedItem => {
            // expect([1, 2, 3, 4]).toEqual(expect.arrayContaining([1, 2, 3])); // ✅
            expect(result).toContainEqual(expect.arrayContaining(expectedItem));
        });
    });


    test('三数之和，不重复2 [-2, 0, 1, 1, 2]', () => {
        const result = threeSum([-2, 0, 1, 1, 2]);
        const expected = [[-2, 0, 2], [-2, 1, 1]];

        // 方法2：使用 toContainEqual 检查每个期望的结果是否存在
        expect(result).toHaveLength(expected.length);
        expected.forEach(expectedItem => {
            // expect([1, 2, 3, 4]).toEqual(expect.arrayContaining([1, 2, 3])); // ✅
            expect(result).toContainEqual(expect.arrayContaining(expectedItem));
        });
    });

    test('使用自定义匹配器', () => {
        const result = threeSum([2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4, 5, 5, -10]);
        //                      -10 -5 -5  -4  -4  -3  -2  -2  0   0  1  2  2   2  2  5  5 
        const expected = [[-10, 5, 5], [-5, 0, 5], [-4, 2, 2], [-3, -2, 5], [-3, 1, 2], [-2, 0, 2]];
        //               [[-10,5,5],    [-5,0,5]]    [-4,2,2],             [-3,1,2],         [-2,0,2], 

        // 方法3：自定义匹配函数
        const arrayContainsAllElements = (actual: number[][], expected: number[][]) => {
            if (actual.length !== expected.length) return false;

            const sortedActual = actual.map(arr => [...arr].sort()).sort();
            const sortedExpected = expected.map(arr => [...arr].sort()).sort();

            return JSON.stringify(sortedActual) === JSON.stringify(sortedExpected);
        };

        expect(arrayContainsAllElements(result, expected)).toBe(true);
    });
})