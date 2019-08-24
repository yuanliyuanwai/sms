package cn.edu.bit.sms.common.vo.res;

import cn.edu.bit.sms.common.annotation.Comment;

public class IdNameVO implements Comparable<IdNameVO> {

    @Comment("编号")
    private Long id;

    @Comment("名称")
    private String name;
    
    public IdNameVO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
		IdNameVO other = (IdNameVO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int compareTo(IdNameVO o) {
		
		return this.id.compareTo(o.id);
	}

	@Override
	public String toString() {
		return "IdNameVO [id=" + id + ", name=" + name + "]";
	}
	
	
}
