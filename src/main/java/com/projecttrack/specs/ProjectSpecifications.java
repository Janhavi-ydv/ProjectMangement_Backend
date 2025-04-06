package com.projecttrack.specs;

import com.projecttrack.model.Project;
import com.projecttrack.model.Department;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecifications {

    public static Specification<Project> hasYear(Integer year) {
        return (root, query, cb) ->
                year == null ? null : cb.equal(root.get("year"), year);
    }

    public static Specification<Project> hasType(String type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("projectType"), type);
    }

    public static Specification<Project> hasDepartment(Department department) {
        return (root, query, cb) ->
                department == null ? null : cb.equal(root.get("department"), department);
    }
}
