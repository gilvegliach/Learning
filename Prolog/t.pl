mem(X, [X|_]) :- !.
mem(X, [_|Y]) :- mem(X,Y).

person(adam).
person(X) :- person(Y), mother(X, Y).

islist([]).
islist([_|_]).


/*conv(you, i).
conv(are, am_not).
conv(do, no).
conv(french, german).
*/

conv(gil, patrizia).
conv(X,X).



alter([],[]).
alter([ WORD_1 | X ], [ WORD_2 | Y ]) :- conv(WORD_1, WORD_2),!, alter(X, Y).




fuel_consumed(waster,   [3.1, 10.4, 15.9, 10.3]).
fuel_consumed(guzzled,  [3.2, 9.9, 13.0, 11.6]).
fuel_consumed(prodigal, [2.8, 9.8, 13.1, 10.4]).

significally_better(Good, Bad) :-
    Threshold is (Good + Bad) / 20,
    Worst is Bad - Threshold,
    Good < Worst.

prefer(Car1, Car2) :-
    fuel_consumed(Car1, Con1),
    fuel_consumed(Car2, Con2),
    sometime_better(Con1, Con2).
    
sometime_better([Con1|_], [Con2|_]) :-
    significally_better(Con1, Con2).

sometime_better([_|X], [_|Y]):-
    sometime_better(X,Y).
    
order(X,Y) :- X < Y.   
    
insort([], []).
insort([X|L], M) :- insort(L, N), insortx(X, N, M).
insortx(X, [A|L], [A|M]) :-
    order(A,X), !, insortx(X, L, M).
insortx(X, L, [X|L]).
        
busort(L, S) :-
    append(X, [A,B|Y], L),
    order(B,A), !,
    append(X, [B,A| Y], M),
    busort(M, S).
busort(L, L).
    
split(H, [A|X], [A|Y], Z) :-
    order(A,H), split(H, X, Y, Z).
split(H, [A|X], Y, [A|Z]) :- \+(order(A, H)), split(H, X, Y, Z).
split(_, [], [], []).
    
quisort([], []).
quisort([H|T], S) :-
    split(H, T, A, B),
    quisort(A, A1),
    quisort(B, B1),
    append(A1, [H|B1], S).
 
quisortx([], X, X).
quisortx([H|T], S, X) :-
    split(H, T, A, B),
    quisortx(A, S, [H|Y]),
    quisortx(B,Y,X).
    
/* Programming in Prolog: example pag 66 */

basicpart(rim).     basicpart(spoke). basicpart(rearframe).
basicpart(handles). basicpart(gears). basicpart(bolt).
basicpart(nut).     basicpart(fork).

assembly(bike,  [wheel, wheel, frame]).
assembly(wheel, [spoke, rim, hub]).
assembly(frame, [rearframe, frontframe]).
assembly(frontframe, [fork, handles]).
assembly(hub,   [gears, axle]).
assembly(axle,  [bolt, nut]).
    
partsof(X, [X]) :- basicpart(X).

partsof(X, P) :-
    assembly(X, Subparts),
    partsoflist(Subparts, P).
   
partsoflist([], []).

partsoflist([P|Tail], Total):-
    partsof(P, Headparts),
    partsoflist(Tail, Tailparts),
    append(Headparts, Tailparts, Total).
    
/* version with accumulator 
   Notice that the elements are in reverse order */

partsof1(X, P) :- partsacc1(X, [], P).

partsacc1(X, A, [X|A]) :- basicpart(X).

partsacc1(X, A, P) :-
    assembly(X, Subparts),
    partsacclist1(Subparts, A, P).
    
partsacclist1([], A, A).

partsacclist1([P|Tail], A, Total) :-
    partsacc1(P, A, Hp),
    partsacclist1(Tail, Hp, Total).
    
/* version with difference lists */

partsof2(X, P) :- partsacc2(X, P, []).

partsacc2(X, [X|Hole], Hole) :- basicpart(X).

partsacc2(X, P, Hole) :-
    assembly(X, Subparts),
    partsacclist2(Subparts, P, Hole).
    
partsacclist2([], Hole, Hole).

partsacclist2([P|Tail], Total, Hole) :-
    partsacc2(P, Total, Hole1),               /* the difference is in these two lines */
    partsacclist2(Tail, Hole1, Hole).
    
    
    
traversal(L, R) :- tracc(L, [], R).

tracc([], A, A).
tracc([H|T], A, R) :- tracc(T, [H|A], R). 


tracc1([],[]).
tracc1([H|T], [H|R]):-
   tracc1(T,R).
   
   
/* without the cut ! as the last thing in the first cluse the query
   ?- sum_to(2,X), basicpart(ree).
   would lead prolog to run forever. This is why it will try to satify 
   sum_to(1, Res) with the second rule, from the backtracktracking of 
   the query. 
  */
      
sum_to(1,1) :- !.
sum_to(N, Res) :-
    N1 is N - 1,
    sum_to(N1, Res1),
    Res is Res1 + N.
    
    

is_integer(0).
is_integer(Y) :- is_integer(X), Y is X+1. 

divide(N1, N2, Result) :-
    is_integer(Result),
    Product1 is N2 * Result,
    Product2 is N2 * (Result+1),
    N1 < Product2, N1 >= Product1,
    !.
    
hello1 :- read(X), write(X).

/* pretty print */ 

spaces(0) :- !.
spaces(N) :- write(' '), N1 is N - 1, spaces(N1).

pp([H|T], I) :- !, J is I+3, pp(H, J), ppx(T, J), nl.
pp(X, I) :- spaces(I), write(X), nl.

ppx([],_).
ppx([H|T], I) :- pp(H,I), ppx(T,I).

/* Lookup in a dictionary and insert an element in the dictionary */

lookup(H, w(H,G,_,_), G1) :- !, G=G1.
lookup(H, w(H1,_,Before,_),G) :-
    H @< H1,
    lookup(H, Before, G).
lookup(H, w(H1,_,_,After), G) :-
    H @> H1,
    lookup(H, After, G).

/* some tries */
    
house(gil).
house(patty).
house(bis).
house(joyce).

rep :- rep.
rep.

app([],X,X).
app([X|T], Y, [X|S]) :- app(T,Y,S).


/* get a char until it finds a non-space char */
new_get(X) :- repeat, get_char(X).
get_non_space(X) :- new_get(X), \+ X = ' '.


/* Maze game */
    
d(a,b). d(b,a).
d(b,e). d(e,b).
d(b,c). d(c,b).
d(d,e). d(e,d).
d(c,d). d(d,c).
d(e,f). d(f,e).
d(g,e). d(e,g).
hasphone(g).
    

go(X,X,T,_).
go(X,Y,T,N) :-
    d(X,Z), \+ member(Z,T),
    spaces(N), write('Entering room '), write(Z), nl,
    N1 is N+1,
        go(Z,Y,[Z|T], N1),
    spaces(N), write('Leaving room '), write(Z), nl.

/* ------------------------------------------------
   --------------  N queens  --------------------- */


printRow(_ ,0, _) :- write(' |').
printRow(Size, N, 1) :- 
    write(' | Q'),
    N1 is N - 1,
    printRow(Size, N1, N), !.
 
printBlankRow(0) :- write('--').
printBlankRow(N) :-
    write('----'),
    N1 is N - 1,
    printBlankRow(N1), !.    
    
printRow(Size, N, Q) :-
    write(' |  '), 
    N1 is N - 1,
    Q1 is Q - 1,
    printRow(Size, N1, Q1),  !. 

printBoard(RowLength, 0, _) :- printBlankRow(RowLength), nl.
printBoard(RowLength, N,  [Q | Queens]) :- 
    printBlankRow(RowLength),          nl,
    printRow(RowLength, RowLength, Q), nl,    
    N1 is N - 1,
    printBoard(RowLength, N1, Queens ), !.
 
listNumbers(0, []) :- !.
listNumbers(N, [N | T]) :-
    N1 is N - 1,
    listNumbers(N1, T).
 
nQueens(N, R) :- 
    listNumbers(N, L),
    permutation(L, R), 
    safePosition(R),
    printBoard(N, N, R).
    
    
safePosition([_]).
safePosition([Q | Queens]) :-
    doesnt_attack(Q, 1, Queens),
    safePosition(Queens).

/* Q doesn't attack the rest of the queens */ 
doesnt_attack(_, _, []).
doesnt_attack(Q, RowDistance, [Q1 | Queens]) :-
    Q =\= Q1,
    ColDistance1 is Q1 - Q,
    abs(ColDistance1, ColDistance),
    ColDistance =\= RowDistance,
    RowDistance1 is RowDistance + 1,
    doesnt_attack(Q, RowDistance1, Queens).

/* ------------------------------------------------ */

/* gcd                                              */
/*
Equivalent Scheme Code

(define mcd 
  (lambda (x y)
    (cond (( = x y )  x)
          ((> y  x) (mcd x (- y x )))
                    ( else (mcd (- x y) y )))))
*/

mcd(X, Y, X) :- X == Y, !.
mcd(X, Y, R) :-
    X < Y,
    A is Y - X,
    mcd(X, A, R), !.
mcd(X, Y, R) :-
    X > Y,
    A is X - Y,
    mcd(A, Y, R), !.
   
/* ------------------------------------------------ */
/* gensym... cool! */

:- dynamic current_num/2.

gensym(Root, Atom) :-
    get_num(Root, Num),
    atom_chars(Root, Name1),
    number_chars(Num, Name2),
    append(Name1, Name2, Name),
    atom_chars(Atom, Name).
    
get_num(Root, Num) :-
    retract(current_num(Root, Num1)), !,
    Num is Num1 + 1,
    asserta(current_num(Root, Num)).

get_num(Root,1) :- asserta(current_num(Root,1)).

/* ------------------------------------------------ */
/* FLP exercise 5.4                                 */
count(_, [], 0).
count(X, [X|Xs], Sum) :- count(X, Xs, Sum1), Sum is Sum1 + 1.
count(X, [Y|Xs], Sum) :- X =\= Y, count(X, Xs, Sum).

remove(_, [], []).
remove(X, [X|Xs], Y) :- remove(X, Xs, Y).
remove(X, [Y|Xs], [Y|Ys]) :- remove(X, Xs, Ys).

occurences_count([],[]).
occurences_count([X|Xs], Y) :-
    count(X, [X|Xs], Sum),
    remove(X, Xs, L),
    append([[X, Sum]], Z, Y),
    occurences_count(L, Z).
    
max_list([], Max, _, Max).
max_list([[X, Sum] | Xs], Curr_max, Curr_max_occ, R) :- 
    Sum > Curr_max_occ,
    max_list(Xs, X, Sum, R).
max_list([X|Xs], Curr_max, Curr_max_occ, R) :-
    max_list(Xs, Curr_max, Curr_max_occ, R).
  
most_frequent(L, Item) :- 
    occurences_count(L, R), 
    max_list(R, 0, 0, Item), !.

/* ------------------------------------------------ */
/* FLP exercise 1.1                                 */
direct(frankfurt, san_francisco).
direct(frankfurt, chicago).
direct(san_francisco, honolulu).
direct(honolulu, maui).

connection(X, Y) :- direct(X,Y).
connection(X, Y) :- direct(X,Z), connection(Z,Y).

/* ------------------------------------------------ */
/* FLP exercise 1.2                                 */

add(X, 0, X).
add(X, s(Y), s(Z)) :- add(X, Y, Z).

mul(_, 0, 0).
mul(X, s(Y), Z) :- mul(X, Y, W), add(X, W, Z).

/* ------------------------------------------------ */
/* FLP exercise 1.3                                 */

fact(0, s(0)).
fact(s(X), Z) :- fact(X, W), mul(W, s(X), Z).

/* ------------------------------------------------ */
/* FLP exercise 1.4                                 */
palindrome(L) :- reverse(L, R), L = R.

reverse([], []).
reverse([X|Xs], Z) :- reverse(Xs, Y), append(Y, [X], Z).

/* ------------------------------------------------ */
/* FLP exercise 1.5                                 */

delelem(1, [X|Xs], X, Xs).
delelem(N, [X|Xs], Elem, [X|RL]) :-
    N1 is N - 1,
    delelem(N1, Xs, Elem, RL).

/* ------------------------------------------------ */
/* FLP exercise 1.6                                 */

sorted([]).
sorted([_]).
sorted([X,Y|Xs]) :- X =< Y, sorted([Y|Xs]).

/* ------------------------------------------------ */
/* FLP exercise 5.4                                 */
sub(Xs, Ys) :- app(Xs, _, Zs), app(_, Zs, Ys).

app([], Ys, Ys).
app([X|Xs], Ys, [X|Zs]) :- app(Xs, Ys, Zs).

/* ------------------------------------------------ */
/* FLP exercise 6.1                                 */

r(a).
r(b).
q(a) :- r(X), !, p(a).
q(f(X)) :- r(X).
p(X) :- r(X).
p(f(X)) :- q(X), !, r(X).
p(g(X)) :- r(X).

