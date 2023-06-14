import {BasicColumn} from '/@/components/Table';
import {FormSchema} from '/@/components/Table';
import { rules} from '/@/utils/helper/validator';
import { render } from '/@/utils/common/renderUtils';
//列表数据
export const columns: BasicColumn[] = [
   {
    title: '风险情况',
    align:"center",
    dataIndex: 'riskInfo'
   },
   {
    title: '风险数量',
    align:"center",
    dataIndex: 'riskAccount'
   },
   {
    title: '是否处置',
    align:"center",
    dataIndex: 'isDispose_dictText'
   },
   {
    title: '处置情况',
    align:"center",
    dataIndex: 'disposeSituation'
   },
   {
    title: '是否问责',
    align:"center",
    dataIndex: 'isAccountability_dictText'
   },
   {
    title: '问责情况',
    align:"center",
    dataIndex: 'accountabilitySituation'
   },
   {
    title: '问责文件',
    align:"center",
    dataIndex: 'accountabilityFile',
    slots: { customRender: 'fileSlot' },
   },
];
//查询数据
export const searchFormSchema: FormSchema[] = [
];
//表单数据
export const formSchema: FormSchema[] = [
  {
    label: '是否自举',
    field: 'isSelf',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '被举报人',
    field: 'reportedPersion',
    component: 'JSearchSelect',
    componentProps:{
       dict:""
    },
  },
  {
    label: '风险情况',
    field: 'riskInfo',
    component: 'InputTextArea',
  },
  {
    label: '风险数量',
    field: 'riskAccount',
    component: 'InputNumber',
  },
  {
    label: '是否处置',
    field: 'isDispose',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '处置情况',
    field: 'disposeSituation',
    component: 'InputTextArea',
  },
  {
    label: '是否问责',
    field: 'isAccountability',
    component: 'JDictSelectTag',
    componentProps:{
        dictCode:""
     },
  },
  {
    label: '问责情况',
    field: 'accountabilitySituation',
    component: 'InputTextArea',
  },
  {
    label: '问责文件',
    field: 'accountabilityFile',
    component: 'JUpload',
    componentProps:{
     },
  },
	// TODO 主键隐藏字段，目前写死为ID
	{
	  label: '',
	  field: 'id',
	  component: 'Input',
	  show: false
	},
];



/**
* 流程表单调用这个方法获取formSchema
* @param param
*/
export function getBpmFormSchema(_formData): FormSchema[]{
  // 默认和原始表单保持一致 如果流程中配置了权限数据，这里需要单独处理formSchema
  return formSchema;
}