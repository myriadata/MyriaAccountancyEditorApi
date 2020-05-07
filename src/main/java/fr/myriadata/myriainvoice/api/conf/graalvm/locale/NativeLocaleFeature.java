package fr.myriadata.myriainvoice.api.conf.graalvm.locale;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

@AutomaticFeature
public class NativeLocaleFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        ImageSingletons
                .lookup(RuntimeClassInitializationSupport.class)
                .initializeAtBuildTime(LocaleSupport.class, this.getClass().getName());

        ImageSingletons.add(LocaleSupport.class, new LocaleSupport());
    }

}
