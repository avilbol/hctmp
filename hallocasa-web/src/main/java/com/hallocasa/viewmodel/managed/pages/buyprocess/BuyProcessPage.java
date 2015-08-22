package com.hallocasa.viewmodel.managed.pages.buyprocess;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import com.hallocasa.dataentities.BlogArticle;
import com.hallocasa.services.interfaces.BlogArticleServicesLocal;
import com.hallocasa.commons.constants.SystemConstants;
import com.hallocasa.model.application.ConstantsProvider;
import com.hallocasa.model.session.WebSession;
import com.hallocasa.view.ViewEnum;
import com.hallocasa.viewmodel.managed.base.BaseManagedBean;
import com.hallocasa.viewmodel.viewfacade.AbstractViewFacade;

/**
 * Backing bean for buy-process index page
 *
 * @author david
 */
@ManagedBean(name = "buyProcessPage")
@ViewScoped
public class BuyProcessPage extends BaseManagedBean {

    /* Static variables */
    private static final long serialVersionUID = 6677832641482281707L;
    private static final String PATH = "/pages/buyprocess/";
    private static final String SECTION_PURCHASE = "buy-process-content.xhtml";
    private static final String SECTION_DOWNLOADS = "downloads.xhtml";
    private static final String SECTION_FAQ = "faq.xhtml";
    private static final String QUERY_STRING_OPTION = "option";

    /* Instance variables */
    private String contentUrl;
    private String faqUrl;
    private String downloadUrl;

    public enum MenuOption {

        CONTENT(SECTION_PURCHASE),
        DOWNLOADS(SECTION_DOWNLOADS),
        FAQ(SECTION_FAQ);

        private final String sectionPath;

        private MenuOption(String sectionPath) {
            this.sectionPath = sectionPath;
        }
    }

    /* Dependencies */
    @EJB
    private BlogArticleServicesLocal blogArticleServices;
    private AbstractViewFacade viewFacade;
    private WebSession webSession;

    /* Instance variables */
    private MenuOption activeMenuOption;
    private List<LinkedBlogArticle> buyProcessArticles;
    private LinkedBlogArticle selectedArticle;
    private LinkedBlogArticle likePropertyArticle;
    private LinkedBlogArticle contacAgentArticle;
    private LinkedBlogArticle brokerSendDetailsArticle;
    private LinkedBlogArticle contactAppraiserArticle;
    private LinkedBlogArticle negotiatePriceArticle;
    private LinkedBlogArticle transferMoneyArticle;
    private LinkedBlogArticle investMore2010Article;
    private LinkedBlogArticle flyColombiaArticle;
    private LinkedBlogArticle representativeSignArticle;
    private LinkedBlogArticle buyFromAbroadArticle;
    private LinkedBlogArticle youSignArticle;
    private LinkedBlogArticle enteredLandRegistryArticle;
    private BlogArticle faqArticle;
    private BlogArticle buyProcessSummaryArticle;
    private BlogArticle buyProcessDetailIntroArticle;

    /**
     * Initialize
     */
    @PostConstruct
    public void initialize() {
        buyProcessArticles = new ArrayList<>();
        viewFacade = AbstractViewFacade.getCurrentInstance();
        webSession = WebSession.getCurrentInstance();

        // initialize urls
        faqUrl = BuyProcessPage.buildPageUrl(MenuOption.FAQ);
        contentUrl = BuyProcessPage.buildPageUrl(MenuOption.CONTENT);
        downloadUrl = BuyProcessPage.buildPageUrl(MenuOption.DOWNLOADS);

        // load articles
        enteredLandRegistryArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_ENTERED_LAND_REGISTRY, null);

        youSignArticle = loadArticle(BlogArticle.COLOMBIA_BUY_PROCESS_YOU_SIGN,
                enteredLandRegistryArticle);
        flyColombiaArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_FLY_TO_COLOMBIA,
                youSignArticle);

        representativeSignArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_REPRESENTATIVE_SIGN,
                enteredLandRegistryArticle);
        buyFromAbroadArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_YOU_BUY_FROM_ABROAD,
                representativeSignArticle);

        investMore2010Article = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_INVEST_MORE_THAN_210, null);
        transferMoneyArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_TRANSFER_MONEY, null);

        negotiatePriceArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_NEGOTIATE_PRICE,
                transferMoneyArticle);
        contactAppraiserArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_CONTACT_APPRAISER,
                negotiatePriceArticle);
        brokerSendDetailsArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_BROKER_SEND_DETAILS,
                contactAppraiserArticle);
        contacAgentArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_CONTACT_AGENT,
                brokerSendDetailsArticle);
        likePropertyArticle = loadArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_LIKE_PROPERTY,
                contacAgentArticle);

        buyProcessArticles.add(likePropertyArticle);
        buyProcessArticles.add(contacAgentArticle);
        buyProcessArticles.add(brokerSendDetailsArticle);
        buyProcessArticles.add(contactAppraiserArticle);
        buyProcessArticles.add(negotiatePriceArticle);
        buyProcessArticles.add(transferMoneyArticle);
        buyProcessArticles.add(buyFromAbroadArticle);
        buyProcessArticles.add(representativeSignArticle);
        buyProcessArticles.add(flyColombiaArticle);
        buyProcessArticles.add(youSignArticle);
        buyProcessArticles.add(enteredLandRegistryArticle);

        selectedArticle = buyProcessArticles.get(0);

        // faq article
        faqArticle = blogArticleServices
                .findBlogArticle(BlogArticle.COLOMBIA_BUY_PROCESS_FAQ);

        // buy process summary article
        buyProcessSummaryArticle = blogArticleServices
                .findBlogArticle(BlogArticle.COLOMBIA_BUY_PROCESS_SUMMARY);
        buyProcessDetailIntroArticle = blogArticleServices.findBlogArticle(
                BlogArticle.COLOMBIA_BUY_PROCESS_DETAIL_INTRO);

        // select section
        String requestedOptionStr
                = viewFacade.getViewParams().get(QUERY_STRING_OPTION);
        requestedOptionStr = requestedOptionStr == null
                ? MenuOption.CONTENT.name() : requestedOptionStr.toUpperCase();

        // identify requested option
        MenuOption requestedMenuOption;
        try {
            requestedMenuOption = MenuOption.valueOf(requestedOptionStr);
        } catch (IllegalArgumentException e) {
            requestedMenuOption = MenuOption.CONTENT;
        }
        activeMenuOption = requestedMenuOption;

    }

    /**
     * Loads an article from the database and put it into a wrapped
     *
     * @param articleId
     * @return
     */
    private LinkedBlogArticle loadArticle(int articleId,
            LinkedBlogArticle nextArticle) {
        BlogArticle blogArticle = blogArticleServices
                .findBlogArticle(articleId);

        LinkedBlogArticle linkedBlogArticle = new LinkedBlogArticle();
        linkedBlogArticle.setBlogArticle(blogArticle);
        linkedBlogArticle.setNextBlogArticle(nextArticle);
        return linkedBlogArticle;
    }

    /* Methods */

    /* Listeners */
    public void onMenuItemClick(MenuOption menuOption) {
        activeMenuOption = menuOption;
    }

    /**
     * Listener for the buy-process item click
     *
     * @param index
     */
    public void onBuyProcessItemClick(int index) {
        selectedArticle = buyProcessArticles.get(index);
    }

    /**
     * Listener for the next of the articles in buy process
     *
     * @param event
     */
    public void onNextArticle(ActionEvent event) {
        selectedArticle = selectedArticle.getNextBlogArticle();
    }

    /**
     *
     * @param event
     */
    public void onBuyFromAbroadClick(ActionEvent event) {
        selectedArticle = buyFromAbroadArticle;
    }

    /**
     *
     * @param event
     */
    public void onFlyToColombiaClick(ActionEvent event) {
        selectedArticle = flyColombiaArticle;
    }

    /* Getter & Setters */
    /**
     * @return the activeSection
     */
    public String getActiveSection() {
        return PATH + activeMenuOption.sectionPath;
    }

    /**
     * @return the activeSectionIndex
     */
    public int getActiveSectionIndex() {
        return activeMenuOption.ordinal();
    }

    /**
     * @return the buyProcessArticles
     */
    public List<LinkedBlogArticle> getBuyProcessArticles() {
        return buyProcessArticles;
    }

    /**
     * return selectedArticle
     *
     * @return
     */
    public LinkedBlogArticle getSelectedArticle() {
        return selectedArticle;
    }

    /**
     * @return the likePropertyArticle
     */
    public LinkedBlogArticle getLikePropertyArticle() {
        return likePropertyArticle;
    }

    /**
     * @return the contacAgentArticle
     */
    public LinkedBlogArticle getContacAgentArticle() {
        return contacAgentArticle;
    }

    /**
     * @return the brokerSºendDetailsArticle
     */
    public LinkedBlogArticle getBrokerSendDetailsArticle() {
        return brokerSendDetailsArticle;
    }

    /**
     * @return the contentUrl
     */
    public String getContentUrl() {
        return contentUrl;
    }

    /**
     * @return the faqUrl
     */
    public String getFaqUrl() {
        return faqUrl;
    }

    /**
     * @return the downloadUrl
     */
    public String getDownloadUrl() {
        return downloadUrl;
    }

    /**
     * @return the contactAppraiserArticle
     */
    public LinkedBlogArticle getContactAppraiserArticle() {
        return contactAppraiserArticle;
    }

    /**
     * @return the negotiatePriceArticle
     */
    public LinkedBlogArticle getNegotiatePriceArticle() {
        return negotiatePriceArticle;
    }

    /**
     * @return the transferMoneyArticle
     */
    public LinkedBlogArticle getTransferMoneyArticle() {
        return transferMoneyArticle;
    }

    /**
     * @return the investMore2010Article
     */
    public LinkedBlogArticle getInvestMore2010Article() {
        return investMore2010Article;
    }

    /**
     * @return the flyColombiaArticle
     */
    public LinkedBlogArticle getFlyColombiaArticle() {
        return flyColombiaArticle;
    }

    /**
     * @return the representativeSignArticle
     */
    public LinkedBlogArticle getRepresentativeSignArticle() {
        return representativeSignArticle;
    }

    /**
     * @return the buyFromAbroadArticle
     */
    public LinkedBlogArticle getBuyFromAbroadArticle() {
        return buyFromAbroadArticle;
    }

    /**
     * @return the youSignArticle
     */
    public LinkedBlogArticle getYouSignArticle() {
        return youSignArticle;
    }

    /**
     * @return the enteredLandRegistryArticle
     */
    public LinkedBlogArticle getEnteredLandRegistryArticle() {
        return enteredLandRegistryArticle;
    }

    /**
     * @return the faqArticle
     */
    public BlogArticle getFaqArticle() {
        return faqArticle;
    }

    /**
     * @return the buyProcessSummaryArticle
     */
    public BlogArticle getBuyProcessSummaryArticle() {
        return buyProcessSummaryArticle;
    }

    /**
     * Return the title of a buying process article
     *
     * @param index
     * @return
     */
    public String getBuyProcessArticleTitle(int index) {
        return buyProcessArticles.get(index).getBlogArticle().
                getTitleTransalation().getText(webSession.getCurrentLanguage());
    }

    /**
     * Return the title of a buying process article
     *
     * @param index
     * @return
     */
    public String getBuyProcessArticleBody(int index) {
        return buyProcessArticles.get(index).getBlogArticle().
                getBodyTransalation().getText(webSession.getCurrentLanguage());
    }

    /**
     * Builds this page URL
     *
     * @param menuOption
     * @return
     */
    private static String buildPageUrl(MenuOption menuOption) {
        StringBuilder str = new StringBuilder(ViewEnum.BUYING_PROCESS.getAbsoulteUrl());
        str.append("?").append(QUERY_STRING_OPTION).append("=").append(
                menuOption.name().toLowerCase());
        return str.toString();
    }

    /**
     * Return page title
     *
     * @return
     */
    public String getPageTitle() {
        switch (activeMenuOption) {
            case CONTENT:
                return buyProcessSummaryArticle.getTitleTransalation().getText(webSession.getCurrentLanguage());
            case DOWNLOADS:
                return buyProcessSummaryArticle.getTitleTransalation().getText(webSession.getCurrentLanguage());
            case FAQ:
                return faqArticle.getTitleTransalation().getText(webSession.getCurrentLanguage());
            default:
                throw new UnsupportedOperationException("Not yet");
        }
    }

    /**
     * Getter for buyProcessDetailIntroArticle
     *
     * @return
     */
    public BlogArticle getBuyProcessDetailIntroArticle() {
        return buyProcessDetailIntroArticle;
    }

}
