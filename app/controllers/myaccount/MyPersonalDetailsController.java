package controllers.myaccount;

import b2bcustomer.B2BCustomerControllerComponent;
import com.commercetools.sunrise.framework.components.PageHeaderControllerComponentSupplier;
import com.commercetools.sunrise.framework.controllers.cache.NoCache;
import com.commercetools.sunrise.framework.controllers.metrics.LogMetrics;
import com.commercetools.sunrise.framework.hooks.RegisteredComponents;
import com.commercetools.sunrise.framework.reverserouters.myaccount.AuthenticationReverseRouter;
import com.commercetools.sunrise.framework.reverserouters.myaccount.MyPersonalDetailsReverseRouter;
import com.commercetools.sunrise.framework.template.TemplateControllerComponentsSupplier;
import com.commercetools.sunrise.framework.template.engine.TemplateRenderer;
import com.commercetools.sunrise.myaccount.CustomerFinder;
import com.commercetools.sunrise.myaccount.mydetails.MyPersonalDetailsControllerAction;
import com.commercetools.sunrise.myaccount.mydetails.MyPersonalDetailsFormData;
import com.commercetools.sunrise.myaccount.mydetails.SunriseMyPersonalDetailsController;
import com.commercetools.sunrise.myaccount.mydetails.viewmodels.MyPersonalDetailsPageContentFactory;
import com.commercetools.sunrise.sessions.customer.CustomerOperationsControllerComponentSupplier;
import io.sphere.sdk.customers.Customer;
import play.data.FormFactory;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

@LogMetrics
@NoCache
@RegisteredComponents({
        TemplateControllerComponentsSupplier.class,
        PageHeaderControllerComponentSupplier.class,
        CustomerOperationsControllerComponentSupplier.class,
        B2BCustomerControllerComponent.class
})
public final class MyPersonalDetailsController extends SunriseMyPersonalDetailsController {

    private final MyPersonalDetailsReverseRouter myPersonalDetailsReverseRouter;
    private final AuthenticationReverseRouter authenticationReverseRouter;

    @Inject
    public MyPersonalDetailsController(final TemplateRenderer templateRenderer,
                                       final FormFactory formFactory,
                                       final MyPersonalDetailsFormData formData,
                                       final CustomerFinder customerFinder,
                                       final MyPersonalDetailsControllerAction controllerAction,
                                       final MyPersonalDetailsPageContentFactory pageContentFactory,
                                       final MyPersonalDetailsReverseRouter myPersonalDetailsReverseRouter,
                                       final AuthenticationReverseRouter authenticationReverseRouter) {
        super(templateRenderer, formFactory, formData, customerFinder, controllerAction, pageContentFactory);
        this.myPersonalDetailsReverseRouter = myPersonalDetailsReverseRouter;
        this.authenticationReverseRouter = authenticationReverseRouter;
    }

    @Override
    public String getTemplateName() {
        return "my-account-personal-details";
    }

    @Override
    public CompletionStage<Result> handleSuccessfulAction(final Customer updatedCustomer, final MyPersonalDetailsFormData formData) {
        return redirectTo(myPersonalDetailsReverseRouter.myPersonalDetailsPageCall());
    }

    @Override
    public CompletionStage<Result> handleNotFoundCustomer() {
        return redirectTo(authenticationReverseRouter.logInPageCall());
    }
}
