/*
	2019-12-25
	
	Implementation
	
	Time	:	4 ms
	Memory	:	1116 kb
*/

#include <cstdio>
#include <cstring>

enum Plane { U, D, F, B, L, R};
enum Dir { CW, CCW };

char cube[6][3][3];

void initCube()
{
	char temp[6][3][3] = 
	{
		{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}},
		{{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}},
		{{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}},
		{{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}},
		{{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}},
		{{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}}
	};

	memcpy(cube, temp, sizeof(temp));
}

void rotate(Plane p, Dir d)
{
	char temp[3];

	// 평면 p를 회전시킨다.
	if (d == CW)
	{
		temp[0] = cube[p][0][0];
		temp[1] = cube[p][0][1];
		temp[2] = cube[p][0][2];

		cube[p][0][2] = temp[0];
		cube[p][0][1] = cube[p][1][0];
		cube[p][0][0] = cube[p][2][0];
		cube[p][1][0] = cube[p][2][1];
		cube[p][2][0] = cube[p][2][2];
		cube[p][2][1] = cube[p][1][2];
		cube[p][2][2] = temp[2];
		cube[p][1][2] = temp[1];
	}
	else
	{
		temp[0] = cube[p][0][0];
		temp[1] = cube[p][0][1];
		temp[2] = cube[p][0][2];

		cube[p][0][0] = temp[2];
		cube[p][0][1] = cube[p][1][2];
		cube[p][0][2] = cube[p][2][2];
		cube[p][1][2] = cube[p][2][1];
		cube[p][2][2] = cube[p][2][0];
		cube[p][2][1] = cube[p][1][0];
		cube[p][2][0] = temp[0];
		cube[p][1][0] = temp[1];
	}

	// 평면 p에 인접한 면들을 회전시킨다.
	if (p == U)
	{
		temp[0] = cube[B][0][0];
		temp[1] = cube[B][0][1];
		temp[2] = cube[B][0][2];

		if (d == CW)
		{
			cube[B][0][0] = cube[L][0][0];
			cube[B][0][1] = cube[L][0][1];
			cube[B][0][2] = cube[L][0][2];
			cube[L][0][0] = cube[F][0][0];
			cube[L][0][1] = cube[F][0][1];
			cube[L][0][2] = cube[F][0][2];
			cube[F][0][0] = cube[R][0][0];
			cube[F][0][1] = cube[R][0][1];
			cube[F][0][2] = cube[R][0][2];
			cube[R][0][0] = temp[0];
			cube[R][0][1] = temp[1];
			cube[R][0][2] = temp[2];
		}
		else
		{
			cube[B][0][0] = cube[R][0][0];
			cube[B][0][1] = cube[R][0][1];
			cube[B][0][2] = cube[R][0][2];
			cube[R][0][0] = cube[F][0][0];
			cube[R][0][1] = cube[F][0][1];
			cube[R][0][2] = cube[F][0][2];
			cube[F][0][0] = cube[L][0][0];
			cube[F][0][1] = cube[L][0][1];
			cube[F][0][2] = cube[L][0][2];
			cube[L][0][0] = temp[0];
			cube[L][0][1] = temp[1];
			cube[L][0][2] = temp[2];
		}
	}
	else if (p == D)
	{
		temp[0] = cube[B][2][0];
		temp[1] = cube[B][2][1];
		temp[2] = cube[B][2][2];

		if (d == CW)
		{
			cube[B][2][0] = cube[R][2][0];
			cube[B][2][1] = cube[R][2][1];
			cube[B][2][2] = cube[R][2][2];
			cube[R][2][0] = cube[F][2][0];
			cube[R][2][1] = cube[F][2][1];
			cube[R][2][2] = cube[F][2][2];
			cube[F][2][0] = cube[L][2][0];
			cube[F][2][1] = cube[L][2][1];
			cube[F][2][2] = cube[L][2][2];
			cube[L][2][0] = temp[0];
			cube[L][2][1] = temp[1];
			cube[L][2][2] = temp[2];
		}
		else
		{
			cube[B][2][0] = cube[L][2][0];
			cube[B][2][1] = cube[L][2][1];
			cube[B][2][2] = cube[L][2][2];
			cube[L][2][0] = cube[F][2][0];
			cube[L][2][1] = cube[F][2][1];
			cube[L][2][2] = cube[F][2][2];
			cube[F][2][0] = cube[R][2][0];
			cube[F][2][1] = cube[R][2][1];
			cube[F][2][2] = cube[R][2][2];
			cube[R][2][0] = temp[0];
			cube[R][2][1] = temp[1];
			cube[R][2][2] = temp[2];
		}
	}
	else if (p == F)
	{
		temp[0] = cube[U][2][0];
		temp[1] = cube[U][2][1];
		temp[2] = cube[U][2][2];

		if (d == CW)
		{
			cube[U][2][0] = cube[L][2][2];
			cube[U][2][1] = cube[L][1][2];
			cube[U][2][2] = cube[L][0][2];
			cube[L][2][2] = cube[D][0][2];
			cube[L][1][2] = cube[D][0][1];
			cube[L][0][2] = cube[D][0][0];
			cube[D][0][2] = cube[R][0][0];
			cube[D][0][1] = cube[R][1][0];
			cube[D][0][0] = cube[R][2][0];
			cube[R][0][0] = temp[0];
			cube[R][1][0] = temp[1];
			cube[R][2][0] = temp[2];
		}
		else
		{
			cube[U][2][0] = cube[R][0][0];
			cube[U][2][1] = cube[R][1][0];
			cube[U][2][2] = cube[R][2][0];
			cube[R][0][0] = cube[D][0][2];
			cube[R][1][0] = cube[D][0][1];
			cube[R][2][0] = cube[D][0][0];
			cube[D][0][2] = cube[L][2][2];
			cube[D][0][1] = cube[L][1][2];
			cube[D][0][0] = cube[L][0][2];
			cube[L][2][2] = temp[0];
			cube[L][1][2] = temp[1];
			cube[L][0][2] = temp[2];

		}
	}
	else if (p == B)
	{
		temp[0] = cube[U][0][0];
		temp[1] = cube[U][0][1];
		temp[2] = cube[U][0][2];

		if (d == CW)
		{
			cube[U][0][0] = cube[R][0][2];
			cube[U][0][1] = cube[R][1][2];
			cube[U][0][2] = cube[R][2][2];
			cube[R][0][2] = cube[D][2][2];
			cube[R][1][2] = cube[D][2][1];
			cube[R][2][2] = cube[D][2][0];
			cube[D][2][2] = cube[L][2][0];
			cube[D][2][1] = cube[L][1][0];
			cube[D][2][0] = cube[L][0][0];
			cube[L][2][0] = temp[0];
			cube[L][1][0] = temp[1];
			cube[L][0][0] = temp[2];
		}
		else
		{
			cube[U][0][0] = cube[L][2][0];
			cube[U][0][1] = cube[L][1][0];
			cube[U][0][2] = cube[L][0][0];
			cube[L][2][0] = cube[D][2][2];
			cube[L][1][0] = cube[D][2][1];
			cube[L][0][0] = cube[D][2][0];
			cube[D][2][2] = cube[R][0][2];
			cube[D][2][1] = cube[R][1][2];
			cube[D][2][0] = cube[R][2][2];
			cube[R][0][2] = temp[0];
			cube[R][1][2] = temp[1];
			cube[R][2][2] = temp[2];
		}
	}
	else if (p == L)
	{
		temp[0] = cube[U][0][0];
		temp[1] = cube[U][1][0];
		temp[2] = cube[U][2][0];

		if (d == CW)
		{
			cube[U][0][0] = cube[B][2][2];
			cube[U][1][0] = cube[B][1][2];
			cube[U][2][0] = cube[B][0][2];
			cube[B][2][2] = cube[D][0][0];
			cube[B][1][2] = cube[D][1][0];
			cube[B][0][2] = cube[D][2][0];
			cube[D][0][0] = cube[F][0][0];
			cube[D][1][0] = cube[F][1][0];
			cube[D][2][0] = cube[F][2][0];
			cube[F][0][0] = temp[0];
			cube[F][1][0] = temp[1];
			cube[F][2][0] = temp[2];
		}
		else
		{
			cube[U][0][0] = cube[F][0][0];
			cube[U][1][0] = cube[F][1][0];
			cube[U][2][0] = cube[F][2][0];
			cube[F][0][0] = cube[D][0][0];
			cube[F][1][0] = cube[D][1][0];
			cube[F][2][0] = cube[D][2][0];
			cube[D][0][0] = cube[B][2][2];
			cube[D][1][0] = cube[B][1][2];
			cube[D][2][0] = cube[B][0][2];
			cube[B][2][2] = temp[0];
			cube[B][1][2] = temp[1];
			cube[B][0][2] = temp[2];
		}
	}
	else 
	{
		temp[0] = cube[U][0][2];
		temp[1] = cube[U][1][2];
		temp[2] = cube[U][2][2];

		if (d == CW)
		{
			cube[U][0][2] = cube[F][0][2];
			cube[U][1][2] = cube[F][1][2];
			cube[U][2][2] = cube[F][2][2];
			cube[F][0][2] = cube[D][0][2];
			cube[F][1][2] = cube[D][1][2];
			cube[F][2][2] = cube[D][2][2];
			cube[D][0][2] = cube[B][2][0];
			cube[D][1][2] = cube[B][1][0];
			cube[D][2][2] = cube[B][0][0];
			cube[B][2][0] = temp[0];
			cube[B][1][0] = temp[1];
			cube[B][0][0] = temp[2];
		}
		else
		{
			cube[U][0][2] = cube[B][2][0];
			cube[U][1][2] = cube[B][1][0];
			cube[U][2][2] = cube[B][0][0];
			cube[B][2][0] = cube[D][0][2];
			cube[B][1][0] = cube[D][1][2];
			cube[B][0][0] = cube[D][2][2];
			cube[D][0][2] = cube[F][0][2];
			cube[D][1][2] = cube[F][1][2];
			cube[D][2][2] = cube[F][2][2];
			cube[F][0][2] = temp[0];
			cube[F][1][2] = temp[1];
			cube[F][2][2] = temp[2];
		}
	}
}

int main(void)
{
	int T;
	scanf("%d", &T);

	for (int tc = 0; tc < T; tc++)
	{
		int n;
		scanf("%d", &n);

		initCube();
		for (int i = 0; i < n; i++)
		{
			char temp[3];
			scanf("%s", temp);

			Plane p;
			switch (temp[0])
			{
				case 'U': p = U; break;
				case 'D': p = D; break;
				case 'F': p = F; break;
				case 'B': p = B; break;
				case 'L': p = L; break;
				case 'R': p = R; break;
			}

			Dir d;
			switch (temp[1])
			{
				case '+': d = CW;  break;
				case '-': d = CCW; break;
			}
			
			rotate(p, d);
		}

		for (int i = 0; i < 3; i++)
		{
			for (int j = 0; j < 3; j++)
				printf("%c", cube[U][i][j]);
			printf("\n");
		}
	}

	return 0;
}