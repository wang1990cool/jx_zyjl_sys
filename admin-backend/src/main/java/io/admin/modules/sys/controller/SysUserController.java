package io.admin.modules.sys.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import io.admin.common.annotation.SysLog;
import io.admin.common.base.AbstractController;
import io.admin.common.utils.Constant;
import io.admin.common.utils.PageUtils;
import io.admin.common.utils.R;
import io.admin.common.validator.Assert;
import io.admin.common.validator.group.AddGroup;
import io.admin.modules.sys.entity.SysUserEntity;
import io.admin.modules.sys.form.PasswordForm;
import io.admin.modules.sys.service.SysUserRoleService;
import io.admin.modules.sys.service.SysUserService;
import io.admin.modules.train.entity.TrainScoreEntity;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;


	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params){
		//只有超级管理员，才能查看所有管理员列表
		if(getUserId() != Constant.SUPER_ADMIN){
			params.put("createUserId", getUserId());
		}
		PageUtils page = sysUserService.queryPage(params);

		return R.ok().put("page", page);
	}
	
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.selectById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		getValidatorProvider().validate(user, AddGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.save(user);

		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
//		ViolationBuild.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}

	@PostMapping("/import")
	@RequiresPermissions("sys:user:import")
	public R update(@RequestParam MultipartFile file, @RequestParam("roleName") String roleName,
					@RequestParam("roleId") String roleId) throws Exception {
		System.out.println(roleId + "2323");
		String fileName = file.getOriginalFilename();
		boolean isExcel2003 = true;
		if (fileName.matches("^.+\\.(?i)(xlsx)$")) {
			isExcel2003 = false;
		}

		InputStream is = file.getInputStream();
		Workbook wb = null;
        if (isExcel2003) {
            wb = new HSSFWorkbook(is);
        } else {
			wb = new XSSFWorkbook(is);
        }

		Sheet sheet = wb.getSheetAt(0);

		for (int i = 1; i < sheet.getLastRowNum() + 1; i++) {
			SysUserEntity  user = new SysUserEntity();
			Row row = sheet.getRow(i);

			String username = row.getCell(0).getStringCellValue();
			String usercname = row.getCell(1).getStringCellValue();
			String password = username;

			String unit = null;
			if (row.getCell(2) != null){
				 unit = row.getCell(2).getStringCellValue();
			}

			List<Long> roleIdList = new ArrayList<>();
			roleIdList.add(Long.parseLong(roleId));

			user.setUsername(username);
			user.setUsercname(usercname);
			user.setPassword(password);
			user.setUnit(unit);
			user.setCreateUserId(getUserId());
			user.setRoleIdList(roleIdList);
			user.setStatus(1);
//			user.setRoleName(roleName);

			List<SysUserEntity> entityList = sysUserService.selectList(new EntityWrapper<SysUserEntity>().
					eq("username", username));

			if (entityList.size() > 0) {
				sysUserService.update(entityList.get(0));
			} else {
				sysUserService.save(user);
			}

		}

		return R.ok();
	}

}
