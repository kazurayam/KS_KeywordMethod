# KS_KeywordMethod

This is a Katalon Studio project where I propose a solution to the question raised in a discussion in the Katal User Forum:

- https://forum.katalon.com/t/how-can-a-user-download-the-list-of-katalon-inbuilt-keywords/131454

## My product

- [keyword-browser/index.html](http://kazurayam.github.io/KS_KeywordMethod/keyword-browser/kbr.html)

![kbr-auttype](http://kazurayam.github.io/KS_KeywordMethod/images/kbr-auttype.png)

You can expand the tree of course

![kbr-expanded](http://kazurayam.github.io/KS_KeywordMethod/images/kbr-expanded.png)

So I have re-invented what is bundled in Katalon Studio with name ["Keyword Browser"](https://europe1.discourse-cdn.com/katalon/original/3X/f/a/fa3e83085a8cddad6855a7b0239ea01f6dd7001e.png).

## Other artifacts

- [output by Test Cases/main/printListOfKeyworMethod](http://kazurayam.github.io/KS_KeywordMethod/keywordsList.txt)
- [output by Test Cases/main/generateHtmlDocLinkedToJavadoc](http://kazurayam.github.io/KS_KeywordMethod/keywords-linked-to-javadoc.html)
- [output by Test Cases/main/demonstrateKeywordBook](http://kazurayam.github.io/KS_KeywordMethod/keywordbook-with-javadoc.json)


## Lesson learned

In this project, I had a lot of programming exercises; escpecially 2 issues

### Java Relection API for Method

See the source of [KeywordMethodFactory](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethodFactory.groovy)

This script can scan the class `com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords` to make a list of "Katalon Buit-in WebUI Keywords" using Java Reflection API.

### Jackson ObjectMapper

I used extensively the [Jackson ObjectMapper](https://www.baeldung.com/jackson-object-mapper-tutorial) to serialize a Java object into JSON, to deserialize a JSON into a Java object. See the following sources, for example

- [com.kazurayam.katalon.keyword.KeywordMethod](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethod.groovy)
- [com.kazurayam.katalon.keyword.KeywordMethodSerializer](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethodSerializer.groovy)
- [com.kazurayam.katlaon.keyword.KeywordMethodDeserializer](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Keywords/com/kazurayam/katalon/keyword/KeywordMethodDeserializer.groovy)

It was my first experience to utilized the ObjectMapper to solve my problem. I was impressed how useful it is.

## Size of the source codes

In https://forum.katalon.com/t/how-can-a-user-download-the-list-of-katalon-inbuilt-keywords/131454/8 , I wrote

>You need to link the entry (keyword name) to the javadoc information of each individual methods. That will require 10 times more complicated progragramming than the above snippet.

In fact, I did the complicated programming required to link the keyword name and the javadoc. How big is the additional code?

The `<projectdir>/clock1.sh` script showed to me:

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

On the other hand, the `<projectdir>/cloc2.sh` showed to me:

```
$ ./cloc2.sh
      37 text files.
      37 unique files.
       0 files ignored.

github.com/AlDanial/cloc v 2.00  T=0.03 s (1090.3 files/s, 37600.2 lines/s)
-------------------------------------------------------------------------------
Language                     files          blank        comment           code
-------------------------------------------------------------------------------
Groovy                          37            243             68            965
-------------------------------------------------------------------------------
SUM:                            37            243             68            965
-------------------------------------------------------------------------------
```

The final code set includes 965 lines of Groovy.

So, 965 lines vs 60 lines. The final code set is almost **16 times bigger** than the original snippet.

I wasn't correct very much.


