package didemo;

import com.commercetools.sunrise.common.contexts.RequestScoped;
import com.commercetools.sunrise.common.controllers.SunriseFrameworkController;
import com.commercetools.sunrise.common.pages.SunrisePageData;
import com.commercetools.sunrise.framework.ControllerComponent;
import com.commercetools.sunrise.hooks.SunrisePageDataHook;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletionStage;

import static java.util.Arrays.asList;

@RequestScoped
public final class DiDemoController extends SunriseFrameworkController {
    @Inject
    private InjectionSubject injectionSubject;

    @Inject
    private RequestScopedInjectionSubject requestScopedInjectionSubject;//class declared the scope

    //also demo with reload
    public CompletionStage<Result> show() {
        return doRequest(() -> {
            final DiDemoPage pageContent = new DiDemoPage();
            final List<String> subjects = new LinkedList<>();
            subjects.add("injectionSubject in controller " + injectionSubject.getId());
            subjects.add("requestScopedInjectionSubject in controller " + requestScopedInjectionSubject.getId());
            pageContent.setSubjects(subjects);
            return asyncOk(renderPage(pageContent, "didemo/show"));
        });
    }

    @Override
    public Set<String> getFrameworkTags() {
        return new HashSet<>(asList("whatever"));
    }

    @Inject
    public void setDemoComponent(final DemoComponent component) {
        registerControllerComponent(component);
    }

    private static final class DemoComponent implements ControllerComponent, SunrisePageDataHook {
        @Inject
        private InjectionSubject injectionSubject;
        @Inject
        private RequestScopedInjectionSubject requestScopedInjectionSubject;

        @Override
        public void acceptSunrisePageData(final SunrisePageData sunrisePageData) {
            if (sunrisePageData.getContent() instanceof DiDemoPage) {
                final DiDemoPage diDemoPage = (DiDemoPage) sunrisePageData.getContent();
                final List<String> subjects = diDemoPage.getSubjects();
                subjects.add("injectionSubject in component " + injectionSubject.getId());
                subjects.add("requestScopedInjectionSubject in component " + requestScopedInjectionSubject.getId());
            }
        }
    }
}