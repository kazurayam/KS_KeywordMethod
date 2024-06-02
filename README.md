# KS_KeywordMethod

This is a Katalon Studio project where I propose a solution to the question raised in a discussion in the Katal User Forum:

- https://forum.katalon.com/t/how-can-a-user-download-the-list-of-katalon-inbuilt-keywords/131454

## What I have developed

### Test Cases/main/printListOfKeywordMethod

See the source [here](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Scripts/main/printListOfKeywordMethod/Script1716811853682.groovy)

This script writes a text file where the name of "Katalon Buit-in Keyword" is listed.

The output by the Test Cases: [docs/keywordsList.txt](https://kazurayam.github.io/KS_KeywordMethod/keywordsList.txt)

### Test Cases/main/generateDocLinkedToJavadoc

See the source [here](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Scripts/main/generateDocLinkedToJavadoc/Script1716896356347.groovy)

This script writes a HTML file which contains the list of "Katalon Built-in Keyword" linked to the JavaDoc.

The output by the Test Case: [docs/keywords-linked-to-javadoc.html](https://kazurayam.github.io/KS_KeywordMethod/keywords-linked-to-javadoc.html)

### Test Cases/main/scrapeKatalonJavadocForKeywordDescription

See the source [here](https://github.com/kazurayam/KS_KeywordMethod/blob/master/Scripts/main/scrapeKatalonJavadocForKeywordDescription/Script1716974673457.groovy)

The script opens [the javadoc of Katalon API](https://api-docs.katalon.com/com/kms/katalon/core/webui/keyword/WebUiBuiltInKeywords.html) in browser using Selenium WebDriver, scrape the texts to create a json file which contains the description of the Katalon built-in Keywords.

The output by the Test Cases: [docs/javadoc.json](https://kazurayam.github.io/KS_KeywordMethod/javadoc.json)

I found this json interesting. I found that quite that the published Javadoc of Katalon built-in keywords is not complete. Quite a significant portion of KeywordMethods lacks the description texts.

###

TODO

## My thoughts

Through the programming lesson here, I learned a lot about the Java Reflection API, especially [java.lang.reflect.Method](https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Method.html).

I have released the v1.4.1 which included KeywordBook class. I enjoyed developping the KeywordBook class employing the Jackson Databind JSON utility.


