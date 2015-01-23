package com.gateside.autotesting.Gat.executor;

import com.gateside.autotesting.Gat.executor.StepsExecutor;
import com.gateside.autotesting.Gat.dataobject.EnumObjectManager;
import com.gateside.autotesting.Gat.dataobject.InvokedMethodInfo;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceStepsCase;
import com.gateside.autotesting.Gat.dataobject.testcase.InterfaceTestStep;
import com.gateside.autotesting.Gat.util.GlobalConfig;
import com.gateside.autotesting.Lib.common.SimpleLogger;


public class InterfaceStepsExecutor extends StepsExecutor
{

	public InterfaceStepsExecutor(String caseFilePath,String caseID)
	{
		super(caseID, caseFilePath);
	}
	
	
	@Override
	public void executeCase() throws Exception 
	{
		for(InterfaceTestStep step : ((InterfaceStepsCase)targetCase).Steps)
		{
			GlobalConfig.setStepsParameterFilePath(GlobalConfig.getAutoProjectName()+"DataFiles"+GlobalConfig.getSlash()+"Xmls"+GlobalConfig.getSlash()+step.StepParametersFilePath); //set glocal config for pre step parameters
			SimpleLogger.logInfo(this.getClass(),"executeCase: set step parameter path as "+GlobalConfig.getStepsParameterFilePath());
			InvokedMethodInfo resultInfo=getStepMethodInfo(step);
			SimpleLogger.logInfo(this.getClass(),"executeCase: execute step:"+resultInfo.classFullName+resultInfo.methodName+step.StepParameterID);
			invokeMethod(resultInfo);
		}
		
	}

    
	@Override
    protected InterfaceStepsCase getTestCase() throws Exception
	{
		return (InterfaceStepsCase)getTestObject(this.getCaseID(),EnumObjectManager.IStepsCaseManager);
	}
}
