package br.com.bplm.yesbrasil.activity.base;

/**
 * Created by lucas on 19/09/15.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.NonConfigurationInstance;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.api.BackgroundExecutor;

import br.com.bplm.yesbrasil.R;
import br.com.bplm.yesbrasil.constants.ApiErrors;
import br.com.bplm.yesbrasil.constants.Constants;
import br.com.bplm.yesbrasil.controller.BaseController;
import br.com.bplm.yesbrasil.dialog.LoadDialog;
import retrofit.RetrofitError;

@EActivity
public abstract class BaseActivity extends BaseTrackingActivity {

    private static final String TAG = BaseActivity.class.getSimpleName();

    // Dependencies ----------------------
/*    @Bean
    protected DrawerMenu menu;*/
    @Bean
    @NonConfigurationInstance
    protected BaseController baseController;

    @NonConfigurationInstance
    protected boolean activityIsVisible = true;

    protected LoadDialog dialog;
    protected Retriable retriable;
    protected Retriable lastRetriable;
    @NonConfigurationInstance
    protected Boolean isFirstRunAfterInject = true,
            isFirstRunAfterViews = true;
    protected Boolean formView = false;

    protected MaterialDialog errorDialog;

    // BaseActivity interface methods -------------------------

   /* @ViewById(R.id.tool_bar)
    protected Toolbar toolbar;

    @ViewById(R.id.recycler_view)
    protected RecyclerView mRecyclerView;

    @ViewById(R.id.drawer_layout)
    protected DrawerLayout drawerLayout;*/

    @ViewById
    protected TextView titleBar;

    /*@Bean
    protected DrawerMenuAdapter mAdapter;*/

    protected RecyclerView.LayoutManager mLayoutManager;

    private ActionBarDrawerToggle mDrawerToggle;

    /**
     * Called when the user cancels the NoConnectionDialog
     */
    public void doOnNoConnectionDialogCancel() {
        /*NoConnectionActivity_.intent(this).start();
        if (dialog != null)
            dialog.dismiss();*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Thread.setDefaultUncaughtExceptionHandler(new CrashExceptionHandler(this, MainActivity_.class));
        super.onCreate(savedInstanceState);

    }

    protected void reloadAfterViews() {
    }

    /**
     * Method for loading afterViews methods in the all activities for not
     * duplicate calls
     */
    protected void afterViews() {
    }

    protected void setupNavigationDrawer() {
        /*if (mRecyclerView != null && drawerLayout != null) {

            mRecyclerView.setHasFixedSize(true);
            //mRecyclerView.setAdapter(mAdapter);
            updateAdapter();
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.no, R.string.yes) {

                @Override
                public void onDrawerSlide(View drawerView, float slideOffset) {
                    super.onDrawerSlide(drawerView, 0);
                }

                @Override
                public void onDrawerOpened(View drawerView) {
                    super.onDrawerOpened(drawerView);
                }

                @Override
                public void onDrawerClosed(View drawerView) {
                    super.onDrawerClosed(drawerView);
                }


            }; // Drawer Toggle Object Made
            drawerLayout.setDrawerListener(mDrawerToggle);

            int lockMode = DrawerLayout.LOCK_MODE_UNLOCKED;
            if (this instanceof MainStartActivity)
                lockMode = DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
            drawerLayout.setDrawerLockMode(lockMode);

            mDrawerToggle.syncState();
        }

        menu.setActionBarTitle();*/

    }

   /* public void updateAdapter() {
        mAdapter.update();
    }*/

    @AfterViews
    protected void baseAfterViews() {
        setupNavigationDrawer();

        if (!isFirstRunAfterViews) {
            reloadAfterViews();
            return;
        }

        afterViews();
        isFirstRunAfterViews = false;
    }



    protected void afterInject() {
    }

    protected void reloadAfterInject() {
    }

    @AfterInject
    protected void baseAfterInject() {
        if (!isFirstRunAfterInject) {
            reloadAfterInject();
            return;
        }
        afterInject();
        isFirstRunAfterInject = false;
    }



    /**
     * This method is executed inside MenuUtils to ease creating new items for
     * activities.
     *
     * @param menu The menu instance from onCreateOptionsMenu
     */
    public void addMenuItens(Menu menu) {
    }

    /**
     * Child view property determines if an activity should show back image
     * instead drawer item in the action bar
     *
     * @return True if a child view false otherwise
     */
    public Boolean isChildView() {
        return false;
    }

    /**
     * Sets the action bar title
     *
     * @return the title to set
     */
    public String getActionBarTitle() {
        return getString(R.string.app_name);
    }

    /**
     * Callback to handle specific error codes
     *
     * @param apErrors ApiErrors
     * @return boolean
     */
    protected boolean handleCustomAPIError(ApiErrors apErrors) {
        return false;
    }

    /**
     * Method responsible for retrying Retriables
     */
    @Background(id = Constants.BACKGROUND_TASK_DEFAULT_ID)
    public void retryLast() {
        executeRetrieable();
    }

    /**
     * Method used to execute a retriable and allowing the user control the
     * result of the execution.
     *
     * @param retriable Instance to keep and retry later
     */
    public void executeRetriable(Retriable retriable) {

        this.retriable = retriable;
        executeRetrieable();

    }

    /**
     * Returns the client responsible for calling the server
     *
     * @return the interface that holds all services signatures
     */
   /* public final HttpsAPI httpsClient() {
        return rest.httpsClient();
    }

    public final HttpAPI httpClient() {
        return rest.httpClient();
    }

    public final Track track() {
        return tracking.track();
    }*/

    // Activity Overrides ---------------------------------------
    @Override
    protected void onResume() {
        super.onResume();
        /*menu.configNavigationDrawer();
        menu.updateAdapter();*/
        activityIsVisible = true;
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        activityIsVisible = true;
    }

    // ----------------------------------------------------------

    @Override
    public void setContentView(int layoutResId) {
        super.setContentView(R.layout.activity_yes);
        final RelativeLayout content = (RelativeLayout) findViewById(R.id.content);
        final View view = getLayoutInflater().inflate(layoutResId, content,
                false);
        content.addView(view);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
       /* if (!isFormView() || wrapper != null) {
            super.onBackPressed();
        } else if (!isFinishing()) {
            OmeMaterialDialog.buildExitFormDialog(this, null).show();
        }*/
    }

    @Override
    protected void onStop() {
        super.onStop();
       /* menu.closeDrawer();*/
        activityIsVisible = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityIsVisible = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        activityIsVisible = true;
    }

    @UiThread(propagation = UiThread.Propagation.REUSE)
    public void toogleDrawer() {
        /*if (drawerLayout.isDrawerOpen(mRecyclerView)) {
            drawerLayout.closeDrawer(mRecyclerView);

        } else {
            drawerLayout.openDrawer(mRecyclerView);
        }*/
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* if (android.R.id.home == item.getItemId() && isChildView()) {
            if (!isFormView()) {
                finish();
            } else {
                OmeMaterialDialog.buildExitFormDialog(this, null).show();
            }
            toogleDrawer();
            return super.onOptionsItemSelected(item);
        }
*/
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startActivity(Intent intent) {
        BackgroundExecutor
                .cancelAll(Constants.BACKGROUND_TASK_DEFAULT_ID, true);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        if (wrapper != null)
            intent.putExtra("wrapper", wrapper);

       /* if (!isFormView())
            super.startActivity(intent);
        else {
            OmeMaterialDialog.buildExitFormDialog(this, intent).show();
        }*/
    }

    /**
     * Returns the client responsible for calling the server
     *
     * @return the interface that holds all services signatures
     */
   /* public final HttpsApi httpsClient() {
        return rest.httpsClient();
    }

    public final HttpApi httpClient() {
        return rest.httpClient();
    }
*/
    public void clearError(final EditText field) {
        field.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (field.getText().length() > 0) {
                    //removes error
                    field.setError(null);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });
    }

    public Throwable handleError(RetrofitError error) {
        /**
         * In the case where there is no connection, prompt the user to
         * chose between proceeding or canceling.
         */
        if (error.isNetworkError()) {
            buildRestExceptionDialog();

            return error;
        }

        boolean handled = false;

       /* try {
            final ErrorVO errorVO = (ErrorVO) error.getBodyAs(ErrorVO.class);

            final ApiErrors apiError = ApiErrors
                    .errorForCode(errorVO.getCode());

            trackError(apiError);

            if (apiError == ApiErrors.INVALID_TOKEN
                    || apiError == ApiErrors.TOKEN_NOT_FOUND) {

                doLoginRenewToken();
                handled = true;

            } else
                handled = handleAPIError(errorVO);

        } catch (Exception e) {
            Log.e(TAG, "Error while renewing token or handling custom error", e);
        }*/

        if (!handled) {
            BackgroundExecutor.cancelAll(Constants.BACKGROUND_TASK_DEFAULT_ID,
                    true);
            buildRestExceptionDialog();
        }

        return error;
    }

    @UiThread
    protected void buildRestExceptionDialog() {
        final BaseActivity activity = this;
/*
        if (!isFinishing()) {
            OmeMaterialDialog.buildTwoButtonDialog(activity,
                    R.string.dialog_rest_exception_title, R.string.dialog_rest_exception,
                    new MaterialDialog.ButtonCallback() {
                        @Override
                        public void onPositive(MaterialDialog dialog) {
                            retryLast();
                        }

                        @Override
                        public void onNegative(MaterialDialog dialog) {
                            NoConnectionActivity_.intent(activity).start();
                            dialog.dismiss();
                            activity.finish();
                        }
                    }).show();
        }*/
    }

    private void trackError(ApiErrors apiError) {
        // GTM Tracking login failed
        //trackLoginError(apiError);
    }

    protected void setDefaultResource(String locale) {
        /*Resources res = getResources();
        // Change locale settings in the app.
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = localeForCountryName(locale);
        Log.d(TAG, conf.locale.getDisplayName());
        res.updateConfiguration(conf, dm);*/
    }


    @UiThread
    @SuppressLint("CommitTransaction")
    public void showDialog() {
        /*if (dialog != null && dialog.isVisible())
            return;

        if (this.isFinishing() || !activityIsVisible)
            return;

        dialog = LoadDialog_.builder().build();
        dialog.show(getSupportFragmentManager(), "Load");*/
    }

    public void hideDialog() {
/*
        if (this.isFinishing() || !activityIsVisible)
            return;

        if (dialog != null)
            dialog.dismiss();*/
    }

    @Background(id = Constants.BACKGROUND_TASK_DEFAULT_ID)
    protected void doLoginRenewToken() {
        final Retriable auxRetriable = lastRetriable;

        executeRetriable(new Retriable() {

            @Override
            public void doWork() {
            }
        });
    }

    @UiThread
    protected void renewToken() {
    }

    protected float getOffsetList() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;

        return width * 0.42f;
    }


    @Background(id = Constants.BACKGROUND_TASK_DEFAULT_ID)
    protected void retryLastBeforeLogin(Retriable lastRetriable) {
        executeRetriable(lastRetriable);
    }

    @Background
    protected void executeRetrieable() {

        if (retriable != null) {

            lastRetriable = retriable;

            try {
                retriable.doWork();

            } catch (RetrofitError e) {
                Log.e(TAG, "Error while doing work of retriable", e);
            }
        }
    }

    protected Boolean handleAPIError(/*ErrorVO errorVO*/) {

        /*hideDialog();
        BackgroundExecutor
                .cancelAll(Constants.BACKGROUND_TASK_DEFAULT_ID, true);
        final ApiErrors apiError = ApiErrors.errorForCode(errorVO.getCode());

        if (apiError != null) {

            if (prefs.campaignIsValid().get()
                    && apiError == ApiErrors.INVALID_COUPON) {
                prefs.campaignIsValid().put(false);
                handleCustomAPIError(apiError);
                return true;
            }

            if (handleCustomAPIError(apiError))
                return true;


            showApiErrorDialog(apiError);

            return true;
        }*/

        return false;
    }

    @UiThread
    protected void showApiErrorDialog(ApiErrors apiError) {
        /*if (!isFinishing()) {
            errorDialog = OmeMaterialDialog.buildSimpleDialog(BaseActivity.this, "Erro", getString(apiError.getMessage()));
            errorDialog.show();
        }*/
    }

    public int convertDpToPixel(float dp) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = dp * (metrics.densityDpi / 160f);
        return (int) px;
    }

    // GETTERs & SETTERs ---------
   /* public DrawerMenu getMenu() {
        return menu;
    }

    public Toolbar getToolbar() {
        return toolbar;
    }*/

    public TextView getTitleBar() {
        return titleBar;
    }

    public Boolean isFormView() {
        return formView;
    }

    public void setFormView(Boolean formView) {
        this.formView = formView;
    }


    @Background
    public void logoutCustomer(final String token) {
        final BaseActivity activity = this;
        /*activity.executeRetriable(new Retriable() {
            @Override
            public void doWork() {
                showDialog();
                *//** DO API CALL **//*
                hideDialog();
                MainActivity_.intent(activity).flags(Intent.FLAG_ACTIVITY_SINGLE_TOP).start();
            }
        });*/

    }

    public void doSlideAnimation(final View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.animate()
                    .translationY(0).alpha(0.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            view.setVisibility(View.GONE);
                        }
                    });
        } else {
            view.animate()
                    .alpha(1.0f)
                    .setListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            super.onAnimationStart(animation);
                            view.setVisibility(View.VISIBLE);
                            view.setAlpha(0.0f);
                        }
                    });
        }
    }

    public BaseController getBaseController() {
        return this.baseController;
    }

    /**
     * Interface to hold a block of code that will be retried in case an
     * exception occurs
     */
    public interface Retriable {
        void doWork();
    }

}
