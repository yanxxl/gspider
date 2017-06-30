package xyz.itbang.gspider

import xyz.itbang.gspider.handler.AbstractHandler
import xyz.itbang.gspider.util.Tools

/**
 * Created by yan on 2017/2/16.
 */
class SpiderTest extends GroovyTestCase {

    String luofans = "http://luoyouzhijia.cn"
    String localHi = "http://localhost:8080/hi"

    //官方示例
    void testSpider() {
        def list = [luofans]
        Spider.crawl {
            //role 'server'
            name "爬"
            seeds list
            thread 1
            rounds 3
            maxFetch 3
            //maxWaiting 100
            include '.*audios/\\d.*'

            handle { Page page ->
                println("Handle -> " + page.url)
                println("Title -> " + page.document.title())
                //page.markNoMoreLinks()
            }

            handle('.*audio.*') { Page page ->
                println("Audio -> ${page.document.title()}")
            }

            //handlers DefaultHandler

//            review { Page page ->
//                println("Time -> ${page.endAt.time - page.startAt.time} ms")
//            }
//
//            reviewCrawl {Spider spider,Date startAt,Date endAt ->
//                println "Status : $spider.crawlName --- $startAt - $endAt"
//            }
        }
    }

    void testClient(){
        Spider.crawl {
            role 'client'
            handlers DefaultHandler
        }
    }

    void testReorganize() {
        Page page = new Page(url: "http://luofans.com:8080")
        Spider spider = new Spider()
        println spider.reorganize(page, 'a')
        println spider.reorganize(page, "http://a.com")
        println spider.reorganize(page, "https://a.com")
    }

    void testPageHTML() {
        Page page = new Page()
        page.text = "<p> a nice day.</p>"
        println(page.document)

        page.clearStatus()
        page.text = "<p> a bad day.<a> </p>"
        println(page.document)
    }

    void testJsoup() {
        Tools.setAcceptAllCookies() //ok

        Page page = new Page(url: localHi)
        println page.connection.userAgent("GSpider").get()
        println page.document
        println page.json

        Page pageLuofans = new Page(url: luofans)
        println pageLuofans.document.select('a')*.attr('href')
    }

    void testWaitFor() {
        //Tools.waitFor("Login,登录") //貌似测试运行时，无法从控制台读取数据，直接运行就可以。
    }

    class DefaultHandler extends AbstractHandler{
        @Override
        Page handlePage(Page page) {
            println "Info from default handler @　$page"
        }
    }
}
