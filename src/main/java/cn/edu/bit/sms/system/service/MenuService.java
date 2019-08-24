package cn.edu.bit.sms.system.service;

import java.util.List;

import cn.edu.bit.sms.system.dto.TreeNode;

public interface MenuService {
	
	List<TreeNode> findByParentId(Long parentId, Byte role);

}
