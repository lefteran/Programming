function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
% C = 1;
% sigma = 0.3;

% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%


% iterMatrix=[0.01, 0.03, 0.1, 0.3, 1, 3, 10, 30];
iterMatrix=[0.01, 0.03, 0.1, 0.3, 1, 3, 10, 30];
min_error=100000;
the_error=100000;
% model= svmTrain(X, y, C, @(x1, x2) gaussianKernel(x1, x2, sigma));
for CValue=iterMatrix
	for sigmaValue=iterMatrix
		model= svmTrain(X, y, CValue, @(x1, x2) gaussianKernel(x1, x2, sigmaValue));
		predictions=svmPredict(model,Xval);
		the_error=mean(double(predictions~=yval));
		fprintf('C is %f sigma is %f and the error is %f\n',CValue, sigmaValue, the_error);
		if the_error<min_error
			min_error=the_error;
			C=CValue;
			sigma=sigmaValue;
		end
	end
end
fprintf('Best: C is %f sigma is %f and the error is %f\n',C, sigma, min_error);



% printf('C is %f and sigma is %f\n',CValue, sigmaValue);
		% disp(CValue)
		% disp(sigmaValue)


% for CValue=0.01:3:30
% 	for sigmaValue=0.01

% end

% for i=0.01:i*=3.12:30
% fprintf('%f\n',i);
% % i*=3.12;
% end

% CValue=0.01
% sigmaValue=0.01
% while(CValue<=30)
% 	while(sigmaValue<=30)
% 		fprintf('C is %f and sigma is %f\n',CValue, sigmaValue);
% 		sigmaValue*=3.12;
% 	end
% 	CValue*=3.12;
% end



% =========================================================================

end
