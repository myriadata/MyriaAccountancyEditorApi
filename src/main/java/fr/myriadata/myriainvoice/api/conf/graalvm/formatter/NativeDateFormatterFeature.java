package fr.myriadata.myriainvoice.api.conf.graalvm.formatter;

import com.oracle.svm.core.annotate.AutomaticFeature;
import org.graalvm.nativeimage.ImageSingletons;
import org.graalvm.nativeimage.hosted.Feature;
import org.graalvm.nativeimage.impl.RuntimeClassInitializationSupport;

@AutomaticFeature
public class NativeDateFormatterFeature implements Feature {

    @Override
    public void beforeAnalysis(BeforeAnalysisAccess access) {
        ImageSingletons
                .lookup(RuntimeClassInitializationSupport.class)
                .initializeAtBuildTime(DateFormatterSupport.class, this.getClass().getName());

        ImageSingletons.add(DateFormatterSupport.class, new DateFormatterSupport());
    }
}

