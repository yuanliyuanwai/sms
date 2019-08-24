package cn.edu.bit.sms.system.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class TreeNode {
	
	private String id; // ID
	
	private String parentId;
	
	private String text; // 节点显示
	
	private String icon; // 图标
	
	private boolean leaf; // 是否叶子
	
	private String linkUrl;
	
	private int sort;

	private boolean expanded; // 是否展开
	
	private Set<TreeNode> children = new LinkedHashSet<TreeNode>();
	
	public TreeNode(String id, String text, boolean leaf,
			boolean expanded) {
		super();
		this.id = id;
		this.text = text;
		this.leaf = leaf;
		this.expanded = expanded;		
	}
		
	public TreeNode(String id, String text, String icon, boolean leaf,
			boolean expanded) {
		super();
		this.id = id;
		this.text = text;
		this.icon = icon;
		this.leaf = leaf;
		this.expanded = expanded;		
	}

	public TreeNode() {
		
	}
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	
	public void setParentId(Long parentId) {
		if (parentId == null) this.parentId = null;
		else this.parentId = String.valueOf(parentId);
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setId(long id) {
		this.id = String.valueOf(id);
	}

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}	
	
	public Set<TreeNode> getChildren() {
		List<TreeNode> list = new ArrayList<TreeNode>(children);
		Collections.sort(list, new Comparator<TreeNode>() {
			@Override
			public int compare(TreeNode o1, TreeNode o2) {
				return o1.getSort() - o2.getSort();
			}
		});
		return new LinkedHashSet<TreeNode>(list);
	}
	
	public void addChild(TreeNode child) {
		this.children.add(child);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TreeNode other = (TreeNode) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
}
