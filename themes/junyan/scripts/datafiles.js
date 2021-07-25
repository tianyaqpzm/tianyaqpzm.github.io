hexo.on('generteBefore', function () {
  // 参考
  if (hexo.locals.get) {
    var data = hexo.locals.get('data')
    data && data.temp && (hexo.theme.config = data.temp)
  }
})
