/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.view.components.utils;

import com.hallocasa.commons.vo.ImageContainer;
import com.hallocasa.utils.ApplicationFileUtils;
import com.hallocasa.view.components.base.BaseComponent;
import com.hallocasa.view.context.ViewContext;
import com.hallocasa.viewmodel.security.LoginDialog;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.imageio.stream.FileImageOutputStream;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.primefaces.context.RequestContext;
import org.primefaces.model.CroppedImage;

/**
 *
 * @author Alexander Villamil
 */
@FacesComponent("imageEditorComponent")
@ViewScoped
public class ImageEditorComponent extends BaseComponent {
    
    private static final Logger LOG = Logger.getLogger(LoginDialog.class.getName());
    
    private enum Attributes {

        imageUrl, imageType, readOnly, defaultImageUrl
    }

    private static final Integer MAX_BYTES_PART_SIZE = 5120000;
    
    private Part uncroppedImagePart;

    private Part ppl;
    
    private String contentExt;
    
    private String uncroppedImageResourcesUrl;

    private String uncroppedImageUrl;

    private ImageContainer image;

    private Boolean showImageLoad;

    private Boolean showImageCrop;

    private Boolean validUpload;

    private CroppedImage croppedImage;

    private String relativePath;

    @Inject
    private ViewContext viewContext;

    @Override
    @PostConstruct
    protected void initialize() {
        this.showImageLoad = true;
        this.showImageCrop = false;
        String imageUrl = (String) this.getAttributes()
                .get(Attributes.imageUrl.toString());
        this.image = new ImageContainer(imageUrl);
    }

    @Override
    protected void saveComponent(FacesContext facesContext, HashMap<String, Object> map) {
        map.put("uncroppedImageUrl", this.uncroppedImageUrl);
        map.put("uncroppedImageResourcesUrl", this.uncroppedImageResourcesUrl);
        map.put("contentExt", this.contentExt);
        map.put("showImageLoad", this.showImageLoad);
        map.put("showImageCrop", this.showImageCrop);
        map.put("croppedImage", this.croppedImage);
        map.put("image", this.image);
        map.put("validUpload", this.validUpload);
    }

    @Override
    protected void restoreComponent(FacesContext facesContext, HashMap<String, Object> map) {
        this.uncroppedImageUrl = (String) map.get("uncroppedImageUrl");
        this.uncroppedImageResourcesUrl = (String) map.get("uncroppedImageResourcesUrl");
        this.contentExt = (String) map.get("contentExt");
        this.showImageLoad = (Boolean) map.get("showImageLoad");
        this.croppedImage = (CroppedImage) map.get("croppedImage");
        this.showImageCrop = (Boolean) map.get("showImageCrop");
        this.image = (ImageContainer) map.get("image");
        this.validUpload = (Boolean) map.get("validUpload");
    }
    
    public void onFileUpload() {
        if (this.validUpload) {
            loadContentExt(this.uncroppedImagePart.getContentType());
            File file = loadFileTemplate();
            try (InputStream inputStream = this.uncroppedImagePart.getInputStream();
                    OutputStream o = new FileOutputStream(file);) {
                IOUtils.copy(inputStream, o);
                inputStream.close();
                o.close();
                this.setUncroppedImageUrl(getRelativePath() + "/" + file.getName());
                this.setUncroppedImageResourcesUrl(file.getName());
                createUncroppedImage();
                onNextClick();
            } catch (IOException e) {
            }
        }
    }

    public void onImageCrop() {
         if(croppedImage == null) {
            return;
        }         
        FileImageOutputStream imageOutput;
        File file = loadFileTemplate();
        try {
            imageOutput = new FileImageOutputStream(file);
            imageOutput.write(croppedImage.getBytes(), 0, croppedImage.getBytes().length);
            imageOutput.close();
            this.image.setUrl(getRelativePath() + "/" + file.getName());
            ((ImageContainer)getAttributes().get(Attributes.imageUrl.toString())).setUrl(this.image.getUrl());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('upload-image-dialog').hide();");
            postCropAction();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error al intentar realizar el ajuste de la imagen. {0}", e);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Cropping failed."));
        }
        
    }
    
    private void loadContentExt(String contentType){
        String ctype;
        if(contentType.equals("image/png")){
            ctype = "png";
        }
        else{
            ctype = "jpg";
        }
        setContentExt(ctype);
    }

    private void postCropAction(){
        destroyUncroppedImage();
        this.validUpload = false;
        this.uncroppedImageResourcesUrl = null;
        this.uncroppedImageUrl = null;
        onBackClick();
    }
    
    public void onNextClick() {
        this.showImageLoad = false;
        this.showImageCrop = true;
    }

    public void onBackClick() {
        this.showImageLoad = true;
        this.showImageCrop = false;
    }

    public Part getUncroppedImagePart() {
        return uncroppedImagePart;
    }

    public void setUncroppedImagePart(Part uncroppedImagePart) {
        this.uncroppedImagePart = uncroppedImagePart;
    }

    public Boolean getShowImageLoad() {
        return showImageLoad;
    }

    public void setShowImageLoad(Boolean showImageLoad) {
        this.showImageLoad = showImageLoad;
    }

    public Boolean getShowImageCrop() {
        return showImageCrop;
    }

    public void setShowImageCrop(Boolean showImageCrop) {
        this.showImageCrop = showImageCrop;
    }

    public CroppedImage getCroppedImage() {
        return croppedImage;
    }

    public void setCroppedImage(CroppedImage croppedImage) {
        this.croppedImage = croppedImage;
    }

    public String getUncroppedImageUrl() {
        return uncroppedImageUrl;
    }

    public void setUncroppedImageUrl(String uncroppedImageUrl) {
        this.uncroppedImageUrl = uncroppedImageUrl;
    }

    public ImageContainer getImage() {
        return image;
    }

    public void setImage(ImageContainer image) {
        this.image = image;
    }

    public Boolean getShowNextButton() {
        return uncroppedImageUrl != null && !showImageCrop;
    }

    public Boolean getShowBackButton() {
        return showImageCrop;
    }

    public Boolean getValidUpload() {
        return validUpload;
    }

    public void setValidUpload(Boolean validUpload) {
        this.validUpload = validUpload;
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        if(value == null){
            return;
        }
        Part file = (Part) value;
        this.validUpload = true;
        String contentType = file.getContentType();
        if (!contentType.contains("image")) {
            viewContext.showGlobalErrorMessage("Image.Edition.Invalid.NotImage",
                    "Image.Edition.Invalid.NotImage");
            this.validUpload = false;
            return;
        }
        if (file.getSize() > MAX_BYTES_PART_SIZE) {
            viewContext.showGlobalErrorMessage("Image.Edition.Invalid.TooBig",
                    "Image.Edition.Invalid.TooBig");
            this.validUpload = false;
        }
    }

    public Part getPpl() {
        return ppl;
    }

    public void setPpl(Part ppl) {
        this.ppl = ppl;
    }

    public String getUncroppedImageResourcesUrl() {
        return uncroppedImageResourcesUrl;
    }

    public void setUncroppedImageResourcesUrl(String uncroppedImageResourcesUrl) {
        this.uncroppedImageResourcesUrl = uncroppedImageResourcesUrl;
    }

    public String getContentExt() {
        return contentExt;
    }

    public void setContentExt(String contentExt) {
        this.contentExt = contentExt;
    }
  
    private void createUncroppedImage(){
        String fileNameSource = ApplicationFileUtils.getAbsoluteUrl(getUncroppedImageUrl());
        try{
            File fileSource = new File(fileNameSource);
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
            String fileNameDestiny = servletContext.getRealPath("") + File.separator + "resources" + 
            File.separator + "images" + File.separator + getUncroppedImageResourcesUrl();
            File fileDestiny = new File(fileNameDestiny);
            InputStream i = new FileInputStream(fileSource);
            OutputStream o = new FileOutputStream(fileDestiny);
            IOUtils.copy(i, o);
            i.close();
            o.close();   
        } catch(IOException e){
             viewContext.showGlobalErrorMessage("Image.Edition.Delete.Error",
                    "Image.Edition.Delete.Error");
        }
        
    }
    
    private void destroyUncroppedImage(){
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String fileNameToDestroy = servletContext.getRealPath("") + File.separator + "resources"   + 
                    File.separator + "images" + File.separator + getUncroppedImageResourcesUrl();
        File file = new File(fileNameToDestroy);
        if(!file.delete()){
             viewContext.showGlobalErrorMessage("Image.Edition.Delete.Error",
                    "Image.Edition.Delete.Error");
        };
    }

    private File loadFileTemplate() {
        String ext = getContentExt();
        File dir = new File(ApplicationFileUtils.getAbsolutePath(getRelativePath()));
        String name = String.format("%s.%s", RandomStringUtils.randomAlphanumeric(8), ext);
        return new File(dir, name);
    }

    private String getRelativePath() {
        if (this.relativePath == null) {
            ImageContainer iContainer = (ImageContainer) this.getAttributes()
                    .get(Attributes.imageUrl.toString());
            if (iContainer == null) {
                this.relativePath = "/" + (String) this.getAttributes()
                        .get(Attributes.imageType.toString());
            } else {
                this.relativePath = ApplicationFileUtils.getRelativePath(iContainer.getUrl());
            }
        }
        return this.relativePath;
    }
}
