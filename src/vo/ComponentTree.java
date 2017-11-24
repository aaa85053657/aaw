package vo;

import java.util.List;

public class ComponentTree {

	private Integer id;

	private String text;

	// "state": "closed"
	private String state;

	private String iconCls = "ico_blank";

	private boolean checked = false;

	private List<ComponentTree> children;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<ComponentTree> getChildren() {
		return children;
	}

	public void setChildren(List<ComponentTree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
