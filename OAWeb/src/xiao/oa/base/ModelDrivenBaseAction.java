package xiao.oa.base;

import java.lang.reflect.ParameterizedType;

import com.opensymphony.xwork2.ModelDriven;

public class ModelDrivenBaseAction<T> extends BaseAction implements ModelDriven<T> {

	// ===================== ��ModelDriven��֧�� ====================

	protected T model;

	public ModelDrivenBaseAction() {
		System.out.println("----------> BaseAction.BaseAction()");
		try {
			// ͨ�������ȡT����������
			ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
			Class<T> clazz = (Class<T>) pt.getActualTypeArguments()[0];
			// ͨ�����䴴��model��ʵ��
			model = clazz.newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public T getModel() {
		return model;
	}

}
