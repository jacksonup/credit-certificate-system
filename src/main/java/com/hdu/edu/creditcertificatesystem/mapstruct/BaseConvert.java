package com.hdu.edu.creditcertificatesystem.mapstruct;

import com.hdu.edu.creditcertificatesystem.pojo.request.BaseRequest;
import org.web3j.abi.datatypes.DynamicStruct;

import java.util.List;

/**
 * @author chenyb46701
 * @date 2023/4/3
 */
public interface BaseConvert<E extends DynamicStruct, R extends BaseRequest, D, EX> {
    /**
     * Request转Entity
     *
     * @param request 基础请求
     * @return Entity
     */
    E convert(R request);

    /**
     * Entity转DTO
     *
     * @param entity Entity
     * @return DTO
     */
    D convert(E entity);

    /**
     * 实体列表转DTO列表
     *
     * @param list<E> 实体列表
     * @return List<D> DTO列表
     */
    List<D> list(List<E> list);
}
