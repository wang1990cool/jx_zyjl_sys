package io.admin.common.validator;

import com.baomidou.mybatisplus.toolkit.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.ConstraintViolation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
public class ViolationBuild {

    @Getter
    private Set<Violation> violations;

    public String getMessage() {
        List<String> list = new ArrayList<String>();
        for (Violation violation : violations) {
            list.add(violation.getMessage());
        }
        return list.size() > 0 ? list.get(0) : "";
    }

    public static <T> ViolationBuild build(Set<ConstraintViolation<T>> cvs) {
        Set<Violation> result = new HashSet<Violation>();
        if (CollectionUtils.isNotEmpty(cvs)) {
            for (ConstraintViolation cv : cvs) {
                result.add(new Violation(cv.getMessage(), cv.getRootBean() == null ? null : cv.getRootBean().toString(),
                        cv.getPropertyPath() == null ? null : cv.getPropertyPath().toString(),
                        cv.getInvalidValue()));
            }
        }
        return new ViolationBuild(result);
    }

}
