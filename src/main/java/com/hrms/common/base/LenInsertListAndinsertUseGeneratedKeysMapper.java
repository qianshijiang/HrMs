package com.hrms.common.base;

import java.util.List;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;

/**
 * @author QSJ
 * @date 2019/7/17
 * @param <T>
 */
@tk.mybatis.mapper.annotation.RegisterMapper
public interface LenInsertListAndinsertUseGeneratedKeysMapper<T> {

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertList(List<T> recordList);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @InsertProvider(type = MySpecialProvider.class, method = "dynamicSQL")
    int insertUseGeneratedKeys(T record);
}
