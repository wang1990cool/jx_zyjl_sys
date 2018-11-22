package io.admin.common.base;

import io.admin.common.validator.ValidatorProvider;
import io.admin.modules.sys.entity.SysUserEntity;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.validation.Validator;

/**
 * Controller公共组件
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	protected Validator validator;

	private ValidatorProvider validatorProvider;

	protected ValidatorProvider getValidatorProvider() {
		if (validatorProvider == null) {
			validatorProvider = new ValidatorProvider(validator);
		}
		return validatorProvider;
	}
	
	protected SysUserEntity getUser() {
		return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
