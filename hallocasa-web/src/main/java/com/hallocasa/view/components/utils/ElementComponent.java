package com.hallocasa.view.components.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.NamingContainer;
import javax.faces.component.UIInput;
import javax.faces.component.UINamingContainer;
import javax.faces.context.FacesContext;

import com.hallocasa.dataentities.app.test.TestEntity;
import com.hallocasa.view.components.base.BaseComponent;

@FacesComponent("elementComponent")
@ViewScoped
public class ElementComponent extends BaseComponent implements NamingContainer{

	private List<String> toyList;
	
	private UIInput input;
	
	@Override
    public String getFamily() {
        return UINamingContainer.COMPONENT_FAMILY;
    }
	
	public String myText(){
		return "hijjj";
	}
	
	public void initialize() {
		/*if(toyList == null){
			toyList = Arrays.asList(new String[]{"1","2","3"});
		}*/
		TestEntity tent = (TestEntity) this.getAttributes().get(
				"attr");
		if(tent == null){
			TestEntity newTestEntity = new TestEntity();
			newTestEntity.setOtherAttribute("kjkjkjkjk");
			getAttributes().put("attr", newTestEntity);
		}
	}

	@Override
	public Object saveState(FacesContext context){
		System.out.println("...");
		Map<String, String> objectMap = new HashMap<String, String>();
		objectMap.put("ggg", "interesting");
		return objectMap;
	}
	
	@SuppressWarnings("unchecked")
	public void restoreState(FacesContext context, Object object){
		System.out.println((Map<String, String>) object);
	}
	
	public UIInput getInput() {
		return input;
	}

	public void setInput(UIInput input) {
		this.input = input;
	}

	@Override
	protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		
	}
}
