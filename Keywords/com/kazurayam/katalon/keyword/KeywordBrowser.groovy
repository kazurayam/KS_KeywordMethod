package com.kazurayam.katalon.keyword

import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * Render a KeywordBook instance as a tree in HTML.
 * Using the Bootstrap 5 Tree-view https://github.com/nhmvienna/bs5treeview?tab=readme-ov-file
 */
public class KeywordBrowser {

	private TreeModel model
	private String htmlFileName = "kbr.html"

	public KeywordBrowser() {
		KeywordBook kb = KeywordBook.createKeywordBook()
		kb.injectJavadoc()
		model = toModel(kb)
	}

	public void setHtmlFileName(String name) {
		this.htmlFileName = name	
	}
	
	/**
	 * Given with a KeywordBook object, transform it into a TreeModel object
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
	 }
	 ]
	 }
	 ]
	 }
	 ]
	 *
	 * @param kb
	 * @return
	 */
	static TreeModel toModel(KeywordBook kb) {
		TreeModel tm = new TreeModel()
		for (AUTType autType : kb.autTypes().toSorted()) {
			TreeNode autTypeNode = new TreeNode(autType.toString(), TreeNode.Icon.Folder)
			tm.addTreeNode(autTypeNode)
			for (String group : kb.keywordGroupsOf(autType)) {
				TreeNode groupNode = new TreeNode(group, TreeNode.Icon.Folder)
				autTypeNode.addNode(groupNode)
				for (String methodName : kb.methodNamesOf(autType, group)) {
					TreeNode methodNameNode = new TreeNode(methodName, TreeNode.Icon.Key)
					groupNode.addNode(methodNameNode)
					String description = kb.findKeywordMethodDescription(autType, group, methodName)
					if (description.length() > 0) {
						TreeNode commentNode = new TreeNode(description, TreeNode.Icon.Comment)
						methodNameNode.addNode(commentNode)
					}
				}
			}
		}
		return tm
	}

	public Path writeHtml(Path dir) {
		Files.createDirectories(dir)
		Path file = dir.resolve(htmlFileName)
		String model = model.serialize()
		String content = """
<!doctype html>
<html lang="en">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>bstreeview</title>
	<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" rel="stylesheet"
		  integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
	<link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
		  integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
	<link href="css/bstreeview.css" rel="stylesheet">
</head>

<body>
	<div class="container">
		<div class="content">
			<div class="row">
				<div class="col-md-6 pt-5">
					<div id="tree">

					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
			integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
			integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
			crossorigin="anonymous"></script>

	<script src="js/bstreeview.js">
	</script>
	<script>
		\$(function () {

			var json = ${model};

			\$('#tree').bstreeview({
				data: json,
                expandIcon: 'fa fa-angle-down fa-fw',
                collapseIcon: 'fa fa-angle-right fa-fw',
                indent: 1.25,
                parentsMarginLeft: '1.25rem',
                openNodeLinkOnNewTab: true
			});
		});
	</script>
</body>
</html>
"""
		return Files.writeString(file, content)
	}

	/**
	 * 
	 * @param dir
	 */
	public Path writeCss(Path dir) {
		Path cssDir = dir.resolve("css")
		Files.createDirectories(cssDir)
		Path file = cssDir.resolve("bstreeview.css")
		String content = """
/*
 @preserve
 bstreeview.css
 Version: 1.2.0
 Authors: Sami CHNITER <sami.chniter@gmail.com>
 Copyright 2020
 License: Apache License 2.0
 Project: https://github.com/chniter/bstreeview
*/
.bstreeview {
  position: relative;
  display: -ms-flexbox;
  display: flex;
  -ms-flex-direction: column;
  flex-direction: column;
  min-width: 0;
  word-wrap: break-word;
  background-color: #fff;
  background-clip: border-box;
  border: 1px solid rgba(0,0,0,.125);
  border-radius: .25rem;

  padding: 0;
  overflow: hidden;
}

.bstreeview .list-group {
  margin-bottom: 0;
}

.bstreeview .list-group-item {
  border-radius: 0;
  border-width: 1px 0 0 0;
  padding-top: 0.5rem;
  padding-bottom: 0.5rem;
  cursor: pointer;
}

.bstreeview .list-group-item:hover {
  background-color:#dee2e6;
}

.bstreeview > .list-group-item:first-child {
  border-top-width: 0;
}

.bstreeview .state-icon {
  margin-right: 8px;
  width: 12px;
  text-align: center;
}
.bstreeview .item-icon {
  margin-right: 5px;
}
"""
		return Files.writeString(file, content)
	}



	/**
	 * 
	 * @param dir
	 */
	public Path writeJs(Path dir) {
		Path jsDir = dir.resolve("js")
		Files.createDirectories(jsDir)
		Path file = jsDir.resolve("bstreeview.js")
		String content = """
/*! @preserve
 * bstreeview.js
 * Version: 1.2.1
 * Authors: Sami CHNITER <sami.chniter@gmail.com>
 * Copyright 2020
 * License: Apache License 2.0
 *
 * Project: https://github.com/chniter/bstreeview
 * Project: https://github.com/nhmvienna/bs5treeview (bootstrap 5)
 */
; (function (\$, window, document, undefined) {
  "use strict";
  /**
   * Default bstreeview  options.
   */
  var pluginName = "bstreeview",
      defaults = {
          expandIcon: 'fa fa-angle-down fa-fw',
          collapseIcon: 'fa fa-angle-right fa-fw',
          expandClass: 'show',
          indent: 1.25,
          parentsMarginLeft: '1.25rem',
          openNodeLinkOnNewTab: true

      };
  /**
   * bstreeview HTML templates.
   */
  var templates = {
      treeview: '<div class="bstreeview"></div>',
      treeviewItem: '<div role="treeitem" class="list-group-item" data-bs-toggle="collapse"></div>',
      treeviewGroupItem: '<div role="group" class="list-group collapse" id="itemid"></div>',
      treeviewItemStateIcon: '<i class="state-icon"></i>',
      treeviewItemIcon: '<i class="item-icon"></i>'
  };
  /**
   * BsTreeview Plugin constructor.
   * @param {*} element
   * @param {*} options
   */
  function bstreeView(element, options) {
      this.element = element;
      this.itemIdPrefix = element.id + "-item-";
      this.settings = \$.extend({}, defaults, options);
      this.init();
  }
  /**
   * Avoid plugin conflict.
   */
  \$.extend(bstreeView.prototype, {
      /**
       * bstreeview intialize.
       */
      init: function () {
          this.tree = [];
          this.nodes = [];
          // Retrieve bstreeview Json Data.
          if (this.settings.data) {
              if (this.settings.data.isPrototypeOf(String)) {
                  this.settings.data = \$.parseJSON(this.settings.data);
              }
              this.tree = \$.extend(true, [], this.settings.data);
              delete this.settings.data;
          }
          // Set main bstreeview class to element.
          \$(this.element).addClass('bstreeview');

          this.initData({ nodes: this.tree });
          var _this = this;
          this.build(\$(this.element), this.tree, 0);
          // Update angle icon on collapse
          \$(this.element).on('click', '.list-group-item', function (e) {
              \$('.state-icon', this)
                  .toggleClass(_this.settings.expandIcon)
                  .toggleClass(_this.settings.collapseIcon);
              // navigate to href if present
              if (e.target.hasAttribute('href')) {
                  if (_this.settings.openNodeLinkOnNewTab) {
                      window.open(e.target.getAttribute('href'), '_blank');
                  }
                  else {
                      window.location = e.target.getAttribute('href');
                  }
              }
              else
              {
                  // Toggle the data-bs-target. Issue with Bootstrap toggle and dynamic code
                  \$(\$(this).attr("data-bs-target")).collapse('toggle');
              }
          });
      },
      /**
       * Initialize treeview Data.
       * @param {*} node
       */
      initData: function (node) {
          if (!node.nodes) return;
          var parent = node;
          var _this = this;
          \$.each(node.nodes, function checkStates(index, node) {

              node.nodeId = _this.nodes.length;
              node.parentId = parent.nodeId;
              _this.nodes.push(node);

              if (node.nodes) {
                  _this.initData(node);
              }
          });
      },
      /**
       * Build treeview.
       * @param {*} parentElement
       * @param {*} nodes
       * @param {*} depth
       */
      build: function (parentElement, nodes, depth) {
          var _this = this;
          // Calculate item padding.
          var leftPadding = _this.settings.parentsMarginLeft;

          if (depth > 0) {
              leftPadding = (_this.settings.indent + depth * _this.settings.indent).toString() + "rem;";
          }
          depth += 1;
          // Add each node and sub-nodes.
          \$.each(nodes, function addNodes(id, node) {
              // Main node element.
              var treeItem = \$(templates.treeviewItem)
                  .attr('data-bs-target', "#" + _this.itemIdPrefix + node.nodeId)
                  .attr('style', 'padding-left:' + leftPadding)
                  .attr('aria-level', depth);
              // Set Expand and Collapse icones.
              if (node.nodes) {
                  var treeItemStateIcon = \$(templates.treeviewItemStateIcon)
                      .addClass((node.expanded)?_this.settings.expandIcon:_this.settings.collapseIcon);
                  treeItem.append(treeItemStateIcon);
              }
              // set node icon if exist.
              if (node.icon) {
                  var treeItemIcon = \$(templates.treeviewItemIcon)
                      .addClass(node.icon);
                  treeItem.append(treeItemIcon);
              }
              // Set node Text.
              treeItem.append(node.text);
              // Reset node href if present
              if (node.href) {
                  treeItem.attr('href', node.href);
              }
              // Add class to node if present
              if (node.class) {
                  treeItem.addClass(node.class);
              }
              // Add custom id to node if present
              if (node.id) {
                  treeItem.attr('id', node.id);
              }
              // Attach node to parent.
              parentElement.append(treeItem);
              // Build child nodes.
              if (node.nodes) {
                  // Node group item.
                  var treeGroup = \$(templates.treeviewGroupItem)
                      .attr('id', _this.itemIdPrefix + node.nodeId);
                  parentElement.append(treeGroup);
                  _this.build(treeGroup, node.nodes, depth);
                  if (node.expanded) {
                      treeGroup.addClass(_this.settings.expandClass);
                  }
              }
          });
      }
  });

  // A really lightweight plugin wrapper around the constructor,
  // preventing against multiple instantiations
  \$.fn[pluginName] = function (options) {
      return this.each(function () {
          if (!\$.data(this, "plugin_" + pluginName)) {
              \$.data(this, "plugin_" +
                  pluginName, new bstreeView(this, options));
          }
      });
  };
})(jQuery, window, document);

"""
		return Files.writeString(file, content)
	}
}
