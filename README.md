# KS_KeywordMethod

This is a Katalon Studio project where I propose a solution to the question raised in a discussion in the Katal User Forum:

- https://forum.katalon.com/t/how-can-a-user-download-the-list-of-katalon-inbuilt-keywords/131454

## My product

- [keyword-browser/index.html](http://KS_KeywordMethod/keyword-browser/index.html)

![kbr-auttype](http://KS_KeywordMethod/images/kbr-auttype.png)
![kbr-expanded](http://KS_KeywordMethod/images/kbr-expanded.png)

So I have re-invented what is bundled in Katalon Studio with name ["Keyword Browser"](https://europe1.discourse-cdn.com/katalon/original/3X/f/a/fa3e83085a8cddad6855a7b0239ea01f6dd7001e.png).

## Other artifacts

- [output by Test Cases/main/printListOfKeyworMethod](http://KS_KeywordMethod/keywordList.txt)
- [output by Test Cases/main/generateHtmlLinkedToJavadoc](http://KS_KeywordMethod/keywords-linked-to-javadoc.html)
- [output by Test Cases/main/scrapeKatalonJavadocForKeywordDescription](http://KS_KeywordMethod/javadoc.json)
- [output by Test Cases/main/demonstrateKeywordBook](http://KS_KeywordMethod/keywordbook-with-javadoc.json)


## Lesson learned

### Java Relection API

See the source [here](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Scripts/main/printListOfKeywordMethod/Script1716811853682.groovy)

This script scans the class `com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords` class and makes a list of "Katalon Buit-in WebUI Keyword" using Java Reflection API.

### Serialize and Descrialize Java Objects using Jackson Databind

I used extensively the [Jackson ObjectMapper](https://www.baeldung.com/jackson-object-mapper-tutorial) to serialize a Java object into JSON, to deserialize a JSON into a Java object. See the following sources, for example

- [com.kazurayam.katalon.keyword.KeywordMethod](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethod.groovy)
- [com.kazurayam.katalon.keyword.KeywordMethodSerializer](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethodSerializer.groovy)
- [com.kazurayam.katlaon.keyword.KeywordMethodDeserializer](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethodDeserializer.groovy)

## Size of the source codes

In https://forum.katalon.com/t/how-can-a-user-download-the-list-of-katalon-inbuilt-keywords/131454/8 , I wrote

>You need to link the entry (keyword name) to the javadoc information of each individual methods. That will require 10 times more complicated progragramming than the above snippet.

In fact, I did the complicated programming required to link the keyword name and the javadoc. How big is the additional code?

The [`<projectdir>/clock1.sh` script tells me:

```
$ ./cloc1.sh
       2 text files.
       2 unique files.
       0 files ignored.

github.com/AlDanial/cloc v 2.00  T=0.01 s (171.9 files/s, 7648.8 lines/s)
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Groovy                           2             11             18             60
-------------------------------------------------------------------------------
SUM:                             2             11             18             60
-------------------------------------------------------------------------------
```

The initial snippet had 60 lines of Groovy codes.

On the other hand, the `<projectdir>/cloc2.sh` shows me:

```
$ ./cloc2.sh
      61 text files.
      61 unique files.
       0 files ignored.

github.com/AlDanial/cloc v 2.00  T=0.07 s (883.3 files/s, 21214.7 lines/s)
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Groovy                          37            243             68            965
XML                             23              0              0            183
Properties                       1              2              3              1
-------------------------------------------------------------------------------
SUM:                            61            245             71           1149
-------------------------------------------------------------------------------
```

The final code set includes 1149 lines.

So, 1149 lines vs 60 lines. The final code set is almost 20 times bigger than the original snippet.

I wasn't correct enough.


