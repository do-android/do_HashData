package doext.implement;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import core.helper.DoJsonHelper;
import core.interfaces.DoIHashData;
import core.interfaces.DoIScriptEngine;
import core.interfaces.datamodel.DoIDataSource;
import core.object.DoInvokeResult;
import doext.define.do_HashData_IMethod;
import doext.define.do_HashData_MAbstract;

/**
 * 自定义扩展MM组件Model实现，继承do_HashData_MAbstract抽象类，并实现do_HashData_IMethod接口方法；
 * #如何调用组件自定义事件？可以通过如下方法触发事件：
 * this.model.getEventCenter().fireEvent(_messageName, jsonResult);
 * 参数解释：@_messageName字符串事件名称，@jsonResult传递事件参数对象； 获取DoInvokeResult对象方式new
 * DoInvokeResult(this.getUniqueKey());
 */
public class do_HashData_Model extends do_HashData_MAbstract implements do_HashData_IMethod, DoIHashData, DoIDataSource {

	private JSONObject data;

	public do_HashData_Model() throws Exception {
		super();
		data = new JSONObject();
	}

	/**
	 * 同步方法，JS脚本调用该组件对象方法时会被调用，可以根据_methodName调用相应的接口实现方法；
	 * 
	 * @_methodName 方法名称
	 * @_dictParas 参数（K,V）
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public boolean invokeSyncMethod(String _methodName, JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		if ("getCount".equals(_methodName)) {
			getCount(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("addOne".equals(_methodName)) {
			addOne(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("addData".equals(_methodName)) {
			addData(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("getOne".equals(_methodName)) {
			getOne(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("getData".equals(_methodName)) {
			getData(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("removeOne".equals(_methodName)) {
			removeOne(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("removeData".equals(_methodName)) {
			removeData(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("removeAll".equals(_methodName)) {
			removeAll(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}
		if ("getAll".equals(_methodName)) {
			getAll(_dictParas, _scriptEngine, _invokeResult);
			return true;
		}

		return super.invokeSyncMethod(_methodName, _dictParas, _scriptEngine, _invokeResult);
	}

	/**
	 * 异步方法（通常都处理些耗时操作，避免UI线程阻塞），JS脚本调用该组件对象方法时会被调用， 可以根据_methodName调用相应的接口实现方法；
	 * 
	 * @_methodName 方法名称
	 * @_dictParas 参数（K,V）
	 * @_scriptEngine 当前page JS上下文环境
	 * @_callbackFuncName 回调函数名 #如何执行异步方法回调？可以通过如下方法：
	 *                    _scriptEngine.callback(_callbackFuncName,
	 *                    _invokeResult);
	 *                    参数解释：@_callbackFuncName回调函数名，@_invokeResult传递回调函数参数对象；
	 *                    获取DoInvokeResult对象方式new
	 *                    DoInvokeResult(this.getUniqueKey());
	 */
	@Override
	public boolean invokeAsyncMethod(String _methodName, JSONObject _dictParas, DoIScriptEngine _scriptEngine, String _callbackFuncName) throws Exception {
		// ...do something
		return super.invokeAsyncMethod(_methodName, _dictParas, _scriptEngine, _callbackFuncName);
	}

	/**
	 * 增加数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void addData(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		JSONObject _newData = DoJsonHelper.getJSONObject(_dictParas, "data");
		Map<String, Object> _dataList = DoJsonHelper.getAllKeyValues(_newData);
		for (Entry<String, Object> _entry : _dataList.entrySet()) {
			String _key = _entry.getKey();
			String _value = DoJsonHelper.getText(_entry.getValue(), "");
			data.put(_key, _value);
		}
	}

	/**
	 * 增加一条数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void addOne(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		String _key = DoJsonHelper.getString(_dictParas, "key", "");
		Object _value = DoJsonHelper.get(_dictParas, "value");
		data.put(_key, _value);
	}

	/**
	 * 获取元素个数；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void getCount(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		_invokeResult.setResultInteger(DoJsonHelper.getAllKeyValues(data).size());
	}

	/**
	 * 获取数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void getData(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		JSONArray _keys = DoJsonHelper.getJSONArray(_dictParas, "keys");
		JSONArray _data = new JSONArray();
		for (int i = 0; i < _keys.length(); i++) {
			String _key = _keys.getString(i);
			Object _value = DoJsonHelper.get(data, _key);
			_data.put(_value);
		}
		_invokeResult.setResultArray(_data);
	}

	/**
	 * 获取某一行数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void getOne(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		String _key = DoJsonHelper.getString(_dictParas, "key", "");
		Object _value = DoJsonHelper.get(data, _key);
		JSONObject _node = new JSONObject();
		_node.put(_key, _value);
		_invokeResult.setResultNode(_node);
	}

	/**
	 * 清空数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void removeAll(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		this.data = new JSONObject();
	}

	/**
	 * 根据keys删除多条数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void removeData(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		JSONArray _keys = DoJsonHelper.getJSONArray(_dictParas, "keys");
		for (int i = 0; i < _keys.length(); i++) {
			String _key = _keys.getString(i);
			data.remove(_key);
		}
	}

	/**
	 * 删除某一行数据；
	 * 
	 * @_dictParas 参数（K,V），可以通过此对象提供相关方法来获取参数值（Key：为参数名称）；
	 * @_scriptEngine 当前Page JS上下文环境对象
	 * @_invokeResult 用于返回方法结果对象
	 */
	@Override
	public void removeOne(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		String _key = DoJsonHelper.getString(_dictParas, "key", "");
		data.remove(_key);
	}

	@Override
	public void getAll(JSONObject _dictParas, DoIScriptEngine _scriptEngine, DoInvokeResult _invokeResult) throws Exception {
		_invokeResult.setResultNode(data);
	}

	@Override
	public List<String> getAllKey() {
		return DoJsonHelper.getAllKeys(data);
	}

	@Override
	public Object getData(String _key) throws JSONException {
		return DoJsonHelper.get(data, _key);
	}

	@Override
	public Object getJsonData() throws Exception {
		return data;
	}

	@Override
	public void setData(String _key, Object _data) throws Exception {
		data.put(_key, _data);
	}

	@Override
	public String serialize() throws Exception {
		return data.toString();
	}

	@Override
	public Object unSerialize(String _str) throws Exception {
		return DoJsonHelper.loadDataFromText(_str);
	}
}