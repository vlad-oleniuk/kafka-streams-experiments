package me.oleniuk.learn.kafkastreamstest;

import org.springframework.boot.context.TypeExcludeFilter;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;

import java.io.IOException;
import java.util.Arrays;

public class PackagesExcludeFilter extends TypeExcludeFilter {

    private final String[] packagesToRetain;

    PackagesExcludeFilter(Class<?> testClass) {
        MergedAnnotation<UnitTest> annotation = MergedAnnotations.from(testClass, MergedAnnotations.SearchStrategy.INHERITED_ANNOTATIONS)
                .get(UnitTest.class);
        this.packagesToRetain = annotation.getValue("value", String[].class).orElseThrow();
    }

    @Override
    public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {
        String className = metadataReader.getClassMetadata().getClassName();
        String packageName = className.substring(0, className.lastIndexOf("."));
        boolean rightPackage = Arrays.stream(packagesToRetain).anyMatch(packageName::startsWith);
        boolean testClass = metadataReader.getResource().getFile().getPath().contains("/test-classes/");

        boolean exclude = testClass || !rightPackage;
        System.out.println("Filtering classes: " + metadataReader.getClassMetadata().getClassName() + ". exclude: " + exclude);

        return exclude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PackagesExcludeFilter that = (PackagesExcludeFilter) o;
        return Arrays.equals(packagesToRetain, that.packagesToRetain);
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(packagesToRetain);
        return result;
    }
}
