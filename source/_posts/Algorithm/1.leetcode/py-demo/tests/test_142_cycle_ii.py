from pathlib import Path
import importlib.util
import pytest

# 动态加载目标实现文件
impl_path = Path(__file__).resolve().parent.parent / "src" / "leetcode" / "142.环形链表-ii.py"
spec = importlib.util.spec_from_file_location("lc142", str(impl_path))
if spec is None:
    raise ModuleNotFoundError(f"无法找到或加载模块: {impl_path}")
module = importlib.util.module_from_spec(spec)
if spec.loader is None:
    raise ModuleNotFoundError(f"无法加载模块的加载器: {impl_path}")
spec.loader.exec_module(module)

Solution = module.Solution
ListNode = module.ListNode

def build_list(vals, pos):
    """
    vals: 节点值列表
    pos: 尾节点连接到的索引（-1 表示无环）
    返回 (head, nodes_list)
    """
    if not vals:
        return None, []
    nodes = [ListNode(x) for x in vals]
    for i in range(len(nodes) - 1):
        nodes[i].next = nodes[i + 1]
    if pos != -1:
        nodes[-1].next = nodes[pos]
    return nodes[0], nodes

def test_no_cycle():
    """无环链表"""
    head, _ = build_list([3, 2, 0, -4], -1)
    assert Solution().detectCycle(head) is None

def test_cycle_at_pos_1():
    """环形链表，尾节点连接到索引 1"""
    head, nodes = build_list([3, 2, 0, -4], 1)
    result = Solution().detectCycle(head)
    assert result is nodes[1]

def test_cycle_at_pos_0():
    """环形链表，尾节点连接到索引 0（自身）"""
    head, nodes = build_list([1, 2, 3], 0)
    result = Solution().detectCycle(head)
    assert result is nodes[0]

def test_single_node_cycle():
    """单个节点形成环"""
    head, nodes = build_list([1], 0)
    result = Solution().detectCycle(head)
    assert result is nodes[0]

def test_single_node_no_cycle():
    """单个节点无环"""
    head, _ = build_list([1], -1)
    assert Solution().detectCycle(head) is None

def test_empty_list():
    """空链表"""
    head, _ = build_list([], -1)
    assert Solution().detectCycle(head) is None

def test_cycle_at_last():
    """环形链表，尾节点连接到倒数第二个节点"""
    head, nodes = build_list([1, 2, 3, 4, 5], 3)
    result = Solution().detectCycle(head)
    assert result is nodes[3]

def test_long_cycle():
    """较长链表的环"""
    head, nodes = build_list(list(range(10)), 5)
    result = Solution().detectCycle(head)
    assert result is nodes[5]
