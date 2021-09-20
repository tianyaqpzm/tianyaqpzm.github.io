package com.pei.learn.basic;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.domain.properties.HasName;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class KeXin {

    @Test
    @DisplayName("ReDos攻击")
    public void test_binary_redos() {
        int[] s = {1, 2, 3};
        HashSet<Integer> sets = new HashSet<>();

//        int[] -> Integer 编译报错
//        Arrays.asList(s).stream().forEach(sets::add);

        String[] sb = {"1", "2", "1"};
        ArrayList<String> list = new ArrayList<>(new HashSet<>(Arrays.asList(sb)));

        int[] sc = new int[]{
                1, 2, 2
        };
        String[] arr = Arrays.stream(sc).mapToObj(String::valueOf).toArray(String[]::new);


//        Assertions.assertThat(matcher.matches()).isFalse();
    }

    private JavaClasses classes;

    @Before
    public void arthSetup() {
        classes = new ClassFileImporter().importPackages("com.pei.");

    }


    @Test
    @DisplayName("架构测试例子")
    public void should_not_cross_layer() {
        String adapter = "outbound";
        String business = "business";
        String domian = "domian";
        Architectures.LayeredArchitecture rule = Architectures.layeredArchitecture()
                .layer(adapter)
                .definedBy("com.pei..adapter.outbound..")
                .layer(business)
                .definedBy("com.pei..business..")
                .layer(domian)
                .definedBy("com.pei..domain..dao..")
                .whereLayer(domian)
                .mayOnlyAccessLayers(business)
                .whereLayer(adapter)
                .mayOnlyBeAccessedByLayers(domian, business)
                .ignoreDependency(HasName.Predicates.nameMatching(".*Refactor"), DescribedPredicate.alwaysTrue());
        rule.check(classes);
    }

}
