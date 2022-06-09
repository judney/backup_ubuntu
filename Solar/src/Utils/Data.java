package Utils;


import java.io.BufferedReader;
import java.util.List;

//import java.util.List;

class Data {
    private List address_components;
    private Long id;
    private Boolean children;
    private List groups;

    public Data(BufferedReader resposta) {
		// TODO Auto-generated constructor stub
	}
	public List getAddress() { return address_components; }
    /*public Long getId() { return id; }
    public Boolean getChildren() { return children; }
    public List<Data> getGroups() { return groups; }
    */ 
    public void setAddress(List address_components) { this.address_components = address_components; }
    /*public void setId(Long id) { this.id = id; }
    public void setChildren(Boolean children) { this.children = children; }
    public void setGroups(List<Data> groups) { this.groups = groups; }
    */ 
    public String toString() {
        return String.format("address_components..:%s\n" , address_components);
        //String.format("title:%s,id:%d,children:%s,groups:%s", title, id, children, groups);
    }
}