/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungnd.object;

import java.io.Serializable;

/**
 *
 * @author HOME
 */
public class ErrorObj implements Serializable {
    private String emailError, passwordError;
    private String accIDError, nameError, passwordConError;
    
    private String cakeNameError, priceCakeError, quantityCakeError, createDayError, expDateError;
    private String categoryNameError;
    private String createExpDateError;

    public ErrorObj() {
    }

    public String getEmailError() {
        return emailError;
    }

    public void setEmailError(String emailError) {
        this.emailError = emailError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getAccIDError() {
        return accIDError;
    }

    public void setAccIDError(String accIDError) {
        this.accIDError = accIDError;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public String getPasswordConError() {
        return passwordConError;
    }

    public void setPasswordConError(String passwordConError) {
        this.passwordConError = passwordConError;
    }

    public String getCakeNameError() {
        return cakeNameError;
    }

    public void setCakeNameError(String cakeNameError) {
        this.cakeNameError = cakeNameError;
    }

    public String getPriceCakeError() {
        return priceCakeError;
    }

    public void setPriceCakeError(String priceCakeError) {
        this.priceCakeError = priceCakeError;
    }

    public String getQuantityCakeError() {
        return quantityCakeError;
    }

    public void setQuantityCakeError(String quantityCakeError) {
        this.quantityCakeError = quantityCakeError;
    }

    public String getCreateDayError() {
        return createDayError;
    }

    public void setCreateDayError(String createDayError) {
        this.createDayError = createDayError;
    }

    public String getExpDateError() {
        return expDateError;
    }

    public void setExpDateError(String expDateError) {
        this.expDateError = expDateError;
    }

    public String getCategoryNameError() {
        return categoryNameError;
    }

    public void setCategoryNameError(String categoryNameError) {
        this.categoryNameError = categoryNameError;
    }

    public String getCreateExpDateError() {
        return createExpDateError;
    }

    public void setCreateExpDateError(String createExpDateError) {
        this.createExpDateError = createExpDateError;
    }
    
}
