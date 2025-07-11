var Trie = function() {
  this.children = {}
  this.count = 0 //表示以该处节点构成的串的个数
  this.preCount = 0 // 表示以该处节点构成的前缀的字串的个数
}

Trie.prototype.insert = function(word) {
  let node = this.children
  for (let char of word) {
    if (!node[char]) node[char] = {}
    node = node[char]
    node.preCount += 1
  }
  node.count += 1
}

Trie.prototype.search = function(word) {
  let node = this.children
  for (let char of word) {
    if (!node[char]) return false
    node = node[char]
  }
  return node.count > 0
}

Trie.prototype.startsWith = function(prefix) {
  let node = this.children
  for (let char of prefix) {
    if (!node[char]) return false
    node = node[char]
  }
  return node.preCount > 0
}
