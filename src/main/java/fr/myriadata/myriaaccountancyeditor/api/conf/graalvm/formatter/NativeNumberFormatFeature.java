package fr.myriadata.myriaaccountancyeditor.api.conf.graalvm.formatter;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

@AutomaticFeature
public class NativeNumberFormatFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        ImageSingletons
                .lookup(RuntimeClassInitializationSupport.class)
                .initializeAtBuildTime(NumberFormatSupport.class, this.getClass().getName());

        ImageSingletons.add(NumberFormatSupport.class, new NumberFormatSupport());
    }

}
