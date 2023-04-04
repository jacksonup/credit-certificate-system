package com.hdu.edu.creditcertificatesystem.mapstruct;

import org.web3j.abi.datatypes.DynamicStruct;

/**
 * @author chenyb46701
 * @date 2023/4/3
 */
public interface BaseConvert<E extends DynamicStruct, R, D> {
    /**
     * Request转Entity
     *
     * @param request Request
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
}
