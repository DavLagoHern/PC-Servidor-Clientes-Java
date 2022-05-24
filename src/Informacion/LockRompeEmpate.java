package Informacion;

public class LockRompeEmpate{
	
	private volatile int last[];
	private volatile int in[];
	int M;
	public LockRompeEmpate(int M) {
		this.M = M;
		last = new int[M];
		in = new int[M];
		for(int i = 0; i < M;i++) {
			last[i] = -1;
			last = last;
			in[i] = -1;
			in = in;
		}
	}
	
	public int getLast(int a) {
		return last[a];
	}
	
	public int getIn(int a) {
		return in[a];
	}
	
	public void setLast(int a, int b) {
		last[a] = b;
		last = last;
	}
	
	public void setIn(int a, int b) {
		in[a] = b;
		in = in;
	}
	
	public void takeLock(int i) {
		for(int j = 1; j < M; j++) {
			in[i] = j;
			in = in;
			last[j] = i;
			last = last;
			for(int k = 1; k < M; k++) {
				if(k != i) {
					while((in[k] >=in[i]) && (last[j] == i));
				}
			}

		}
	}
	
	public void releaseLock(int d) {
		in[d] = -1;
		in = in;
	}
	
}
