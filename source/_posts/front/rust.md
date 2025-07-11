---
title: rust
date: 2021-10-07 16:18:00
tags: [前端, rust]
categories:
  - 前端
description: 前端
top: false
---

[TOC]

# Rust

### 安装

```sh
curl --proto '=https' --tlsv1.2 -sSf https://sh.rustup.rs | sh

运行 rustup update 获取最新版本的 Rust。

rustup self uninstall

source "$HOME/.cargo/env"
```







### Cargo：Rust 的构建工具和包管理器

您在安装 Rustup 时，也会安装 Rust 构建工具和包管理器的最新稳定版，即 Cargo。Cargo 可以做很多事情：

- `cargo build` 可以构建项目
- `cargo run` 可以运行项目
- `cargo test` 可以测试项目
- `cargo doc` 可以为项目构建文档
- `cargo publish` 可以将库发布到 [crates.io](https://crates.io/)。

要检查您是否安装了 Rust 和 Cargo，可以在终端中运行：

```
cargo --version
```



```
cargo new hello-rust
```

`Cargo.toml` 为 Rust 的清单文件。其中包含了项目的元数据和依赖库。

`src/main.rs` 为编写应用代码的地方。



