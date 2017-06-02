student(abc).
student(def).
student(ghi).

classmates(X,Y) :- student(X), student(Y).

% Bad attempt at fixing: X is a classmate of X
classmates2(X,Y) :- X \= Y, student(X), student(Y).

% Good attempt at fixing: X is a classmate of X
classmates3(X,Y) :- student(X), student(Y), X \= Y.


%% Merge 2 sorted lists
merge([], X, X).
merge(X, [], X).
merge([H1|T1], [H2|T2], [H1|Z]) :- H1 < H2, merge(T1, [H2|T2], Z).
merge([H1|T1], [H2|T2], [H2|Z]) :- H1 >= H2, merge([H1|T1], T2, Z).


split([], [], []).
split([X],[X],[]).
split([X,Y|L], [X|L1], [Y|L2]) :- split(L, L1, L2).


mergesort([], []).
mergesort([X], [X]).
mergesort(L, Ls) :- split(L, L1, L2), mergesort(L1, L1s), mergesort(L2, L2s), merge(L1s, L2s, Ls).


size([],0).
size([H|T],N) :- size(T,N1), N is N1+1.

member1([X|_], X).
member1([H|T], X):- member1(T, X).

