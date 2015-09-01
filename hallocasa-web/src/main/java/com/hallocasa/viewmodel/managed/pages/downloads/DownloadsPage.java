/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hallocasa.viewmodel.managed.pages.downloads;

import com.hallocasa.dataentities.BlogArticle;
import com.hallocasa.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.model.session.WebSessionImpl;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.component.filedownload.FileDownloadActionListener;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "downloadsPage")
@ViewScoped
public class DownloadsPage extends BaseManagedBean {

    /* Inner Types */
    class DirectoryFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return new File(dir, name).isDirectory();
        }

    }

    class FileFilter implements FilenameFilter {

        @Override
        public boolean accept(File dir, String name) {
            return !(new File(dir, name).isDirectory());
        }
    }

    /* instance attributes */
    private TreeNode root;
    private FileFilter fileFilter;
    private DirectoryFilter directoryFilter;
    private BlogArticle downloadIntroArticle;
    
     /* Dependencies */
    @EJB
    private BlogArticleServicesLocal blogArticleServices;

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {

        // file filters
        fileFilter = new FileFilter();
        directoryFilter = new DirectoryFilter();

        File fileRoot = new File(SystemConstants.DOWNLOADS_PATH + "/"
                + WebSessionImpl.getCurrentInstance().getCurrentLanguage().name());
        root = new DefaultTreeNode("root", null);
        loadFolderFiles(fileRoot, root);
        
        // load downloads introduction article
        downloadIntroArticle = blogArticleServices
                .findBlogArticle(BlogArticle.COLOMBIA_BUY_PROCESS_DOWNLOADS_INTRO);

    }

    /**
     * Loads recursively a folder children and add them as children of the node
     *
     * @param folder
     */
    private void loadFolderFiles(File folder, TreeNode node) {

        // creates directory nodes
        for (String fileChildPath : folder.list(directoryFilter)) {
            File fileChild = new File(folder.getAbsolutePath().concat("/").concat(fileChildPath));
            TreeNode directoryNode = new DefaultTreeNode("directory", fileChild, node);
            directoryNode.setExpanded(true);
            loadFolderFiles(fileChild, directoryNode);
        }

        // creates file nodes
        for (String fileChildPath : folder.list(fileFilter)) {
            File fileChild = new File(folder.getAbsolutePath().concat("/").concat(fileChildPath));
            TreeNode fileNode;
            fileNode = new DefaultTreeNode("file", fileChild, node);
            fileNode.setExpanded(true);
        }
    }

    /* Listeners */
    /**
     * Process click on the tree node to download
     *
     * @param event
     */
    public void processDocumentDownloadClick(ActionEvent event) {
        try {
            File file = (File) event.getComponent().getAttributes().get("file");
            DefaultStreamedContent fileToDownload = new DefaultStreamedContent(
                    new FileInputStream(file), "application/pdf",
                    formatNameToDownload(file.getName()));
            ExpressionFactory ef
                    = FacesContext.getCurrentInstance().getApplication().getExpressionFactory();
            ValueExpression fileExpression = ef.createValueExpression(fileToDownload, StreamedContent.class);
            FileDownloadActionListener listener = new FileDownloadActionListener(fileExpression, null);
            listener.processAction(event);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DownloadsPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Getter for root
     *
     * @return
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Formats name to remove not desired characters
     *
     * @param name
     * @return
     */
    private String formatNameToDownload(String name) {
        //TODO: file names with special character are not well shown in linux
        return name;
    }

    /**
     * Getter for downloadIntroArticle
     *
     * @return
     */
    public BlogArticle getDownloadIntroArticle() {
        return downloadIntroArticle;
    }

}
