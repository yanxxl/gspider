# GSpider

a Java/Groovy Spider,make crawl easy.

一个基于 Java/Groovy 的 Spider，让抓取更简单。

## Feature 特性
- easy to use,support crawl DSL. 易用，支持抓取DSL。
- easy to manipulating html,by transform to jsoup document or json or just text. 操作网页简单，内置可用text, jsoup document,json 等格式。
- config like nutch. 像使用 nutch 一样。配置种子，线程数，抓取数，页面范围（包含和排除），处理器等，就可启动了。
- develop use java or groovy. 可用使用 Java 或者 Groovy 开发。当然也可用于任何JVM支持的语言。
- deploy alone，distribute，and support multi-spider。 可独立部署，分布式部署，并支持多爬虫。

## Example 示例
This is a Groovy script example. 这是一个 Groovy 脚本示例。
```
        Spider.crawl {
            seeds "http://www.luoyouzhijia.cn/"
            handle { Page page ->
                println("Handle -> " + page.url)
                println("Title -> " + page.document.title())
            }
        }
```
[more example 更多示例](https://github.com/yanq/gspider-example)
> **Warning 注意**
> 
> Do not fetch too much before change the seeds，just for test。
> 
> 别抓太多哈，这个网站可是我亲儿子，别整坏了。

## How to use  咋用
Gradle code
```
compile 'xyz.itbang:gspider:3.1.1'
```

## And ...
欢迎点赞，fork，欢迎意见建议，靠这个赚了钱的，欢迎打赏。

