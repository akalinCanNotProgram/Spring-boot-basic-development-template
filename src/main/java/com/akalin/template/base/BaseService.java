package com.akalin.template.base;

import java.io.Serializable;



/**
 * @author Exrickx
 */
// JDK8函数式接口注解 仅能包含一个抽象方法
@FunctionalInterface
public interface BaseService<E, ID extends Serializable>{

    BaseDao<E, ID> getRepository();


    /**
     * 根据ID获取
     * @param id
     * @return
     */
    public default E get(ID id) {
        return getRepository().selectById(id);
    }

    /**
     * 获取所有列表
     * @return
     */
//    public default Page<E> getAll() {
//        return getRepository().selectById();
//    }

    /**
     * 获取总数
     * @return
     */
//    public default Long getTotalCount() {
//        return getRepository().selectCount();
//    }

    /**
     * 保存
     * @param entity
     * @return
     */
    public default int save(E entity) {

        return getRepository().insert(entity);
    }

    /**
     * 修改
     * @param entity
     * @return
     */
    public default int update(E entity) {
        return getRepository().updateById(entity);
    }

    /**
     * 批量保存与修改
     * @param entities
     * @return
     */
//    public default Iterable<E> saveOrUpdateAll(Iterable<E> entities) {
//       Iterator en =  entities.iterator();
//       while (en.hasNext()){
//           E enity =  (E)en.next();
//           getRepository().updateById(enity);
//       }
//        return getRepository().saveAll(entities);
//    }

    /**
     * 根据Id删除
     * @param id
     */
    public default void delete(ID id) {
        getRepository().deleteById(id);
    }



}
