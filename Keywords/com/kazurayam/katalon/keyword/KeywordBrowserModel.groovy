package com.kazurayam.katalon.keyword

public class KeywordBrowserModel {
	
	List<KeywordBrowserNode> root
	
	KeywordBrowserModel() {
		root = new ArrayList<>()
	}
	
	public String serialize() {
		return """
			[
				{
					text: "WebUI",
					icon: "fa fa-folder fa-fw",
					nodes: [
						{
							text: "Alert",
							icon: "fa fa-folder fa-fw",
							nodes: [
								{
									icon: "fa fa-key fa-fw",
									text: "acceptAlert",
									nodes: [
										{
											icon: "fa fa-comment-o fa-fw",
											text: "Fires the Close event of the running application to the Windows System. This action is similar to pressing 'ALT + F4' and also does not force close the application."
										}
									]
								},
							]
						}
					]
				}
			]
"""
	}
}

