package ir.moke.jsf.convert;

import ir.moke.jsf.beans.users.UserManager;
import ir.moke.jsf.model.entities.Role;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "roleConverter", forClass = Role.class)
public class RoleConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        if (s == null)
            return null;
        UserManager userManager = facesContext.getApplication().evaluateExpressionGet(facesContext, "#{userManager}", UserManager.class);
        for (Role role : userManager.getCurrentRoles()) {
            if (role.getRoleName().equals(s)) {
                return role;
            }
        }
        throw new ConverterException(new FacesMessage(String.format("Cannot convert %s to Role", s)));
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        if (o instanceof Role) {
            return ((Role) o).getRoleName();
        } else {
            return null;
        }
    }
}
