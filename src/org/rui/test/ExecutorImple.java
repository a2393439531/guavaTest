package org.rui.test;

import java.util.concurrent.Executor;

public class ExecutorImple implements Executor
{

	@Override
	public void execute(Runnable command)
	{
		command.run();

	}

}
