package com.android.nytimes;

import com.android.nytimes.common.network.RestRequestListener;
import com.android.nytimes.interactor.NYArticleServiceInteractor;
import com.android.nytimes.interactor.NYArticleServiceInteractorImpl;
import com.android.nytimes.model.Result;
import com.android.nytimes.presenter.ArticleListFragmentPresenter;
import com.android.nytimes.presenter.ArticleListFragmentPresenterImpl;
import com.android.nytimes.view.ArticleListFragment;
import com.android.nytimes.view.ArticleListFragmentView;

import org.junit.Before;
import org.junit.Test;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ArticleListFragmentPresenterMockUnitTest {

    private ArticleListFragmentPresenter mArticleListFragmentPresenter;
    //private FakeArticleListFragmentView fakeArticleListFragmentView;
    @Mock
    private ArticleListFragment fakeArticleListFragmentView;

    @Mock
    private NYArticleServiceInteractorImpl fakeNYArticleServiceInteractor;

    @Before
    public void setUp(){
        //fakeArticleListFragmentView = new FakeArticleListFragmentView();
    }


    @Test
    public void test_getArticleList_Success(){
        //NYArticleServiceInteractor fakeNYArticleServiceInteractor = new FakeNYArticleServiceInteractor(true);
        mArticleListFragmentPresenter = new ArticleListFragmentPresenterImpl(fakeArticleListFragmentView, fakeNYArticleServiceInteractor);

        mArticleListFragmentPresenter.getAllArticles("1");

    }

    @Test
    public void test_getArticleList_Fail(){
        //NYArticleServiceInteractor fakeNYArticleServiceInteractor = new FakeNYArticleServiceInteractor(false);
        mArticleListFragmentPresenter = new ArticleListFragmentPresenterImpl(fakeArticleListFragmentView, fakeNYArticleServiceInteractor);

        mArticleListFragmentPresenter.getAllArticles("1");

    }


   /* public class FakeArticleListFragmentView implements ArticleListFragmentView{

        public int size;
        private boolean isErrorDialogShown = false;

        @Override
        public void updateList(List<Result> articleList) {
            if(articleList != null){
                size = articleList.size();
            }
        }

        @Override
        public void navigateTo(String url) {

        }

        @Override
        public void showLoadingSpinner() {
        }

        @Override
        public void dismissLoadingSpinner() {
        }

        @Override
        public void showNetworkErrorDialog() {
            isErrorDialogShown = true;
        }
    }*/

   /* public class FakeNYArticleServiceInteractor implements NYArticleServiceInteractor{

        private final boolean mIsSuccess;

        public FakeNYArticleServiceInteractor(boolean isSuccess){
            mIsSuccess = isSuccess;
        }
        @Override
        public void getArticles(String period, RestRequestListener restRequestListener) {
            if(mIsSuccess)
                restRequestListener.onSuccess(createArticleList());
            else {
                restRequestListener.onFailure(new UnknownHostException());
            }
        }
    }*/

    private List<Result> createArticleList(){
        List<Result> list = new ArrayList<>();
        list.add(new Result());
        list.add(new Result());
        return list;
    }
}
