package com.chriswk.gradle.rest.sec.util;

import org.apache.commons.lang3.tuple.ImmutablePair;
import com.chriswk.gradle.rest.common.IEntity;
import com.chriswk.gradle.rest.util.SearchCommonUtil;
import org.springframework.data.jpa.domain.Specification;

public final class SearchSecUtil {

    private SearchSecUtil() {
throw new UnsupportedOperationException();
    }

    // util

    public static <T extends IEntity> Specification<T> resolveConstraint(final ImmutablePair<String, ?> constraint, final Class<T> clazz) {
String constraintName = constraint.getLeft();
boolean negated = false;
if (constraintName.startsWith(SearchCommonUtil.NEGATION)) {
negated = true;
constraintName = constraintName.substring(1);
}

if (constraintName.equals(SearchCommonUtil.NAME)) {
return QuerySpecifications.getByNameSpecification(clazz, (String) constraint.getRight(), negated);
}
if (constraintName.equals(SearchCommonUtil.ID)) {
return QuerySpecifications.getByIdSpecification(clazz, (Long) constraint.getRight(), negated);
}
return null;
    }

}