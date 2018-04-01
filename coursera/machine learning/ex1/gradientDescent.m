function [theta, J_history] = gradientDescent(X, y, theta, alpha, num_iters)
%GRADIENTDESCENT Performs gradient descent to learn theta
%   theta = GRADIENTDESCENT(X, y, theta, alpha, num_iters) updates theta by 
%   taking num_iters gradient steps with learning rate alpha

% Initialize some useful values
m = length(y); % number of training examples
J_history = zeros(num_iters, 1);


for iter = 1:num_iters

    hypothesis=X*theta;
    errors=hypothesis-y;
    %theta_vec=theta_vec-(alpha+(1/m)*(errors'*X))';

    for j=1:2
        theta(j)=theta(j)-alpha*(1/m)*errors'*X(1:end,j);
    end

    % Save the cost J in every iteration    
    J_history(iter) = computeCost(X, y, theta);
    % fprintf('In iteration %d theta is [%f,%f] and the cost is %f\n', iter, theta(1),theta(2), J_history(iter));

end

end
